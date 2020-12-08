package sexy.tea.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.UserMapper;
import sexy.tea.model.User;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.model.vo.UserVO;
import sexy.tea.service.UserService;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 用户接口实现类
 * <p>
 * TODO JWT、OAuth 2.0、SSO、Spring Security改造用户登录功能
 *
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    /**
     * redis字符串模板, 操作loginSessionId
     */
    private final StringRedisTemplate template;

    /**
     * Redis中存储loginSessionId的前缀
     */
    @Value("${login.session.prefix}")
    private String prefix;

    /**
     * loginSessionId的超时时间
     */
    @Value("${login.session.timeout}")
    private long timeout;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, StringRedisTemplate template) {
        this.userMapper = userMapper;
        this.template = template;
    }

    /**
     * 用户注册
     *
     * @param user 前台传入的表单
     *
     * @return 统一响应对象 {@link Result}
     */
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result register(User user) {

        // md5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("user");
        }
        if (user.getNickname() == null) {
            user.setNickname(user.getUsername());
        }
        log.info("用户注册, 参数: {}", user);

        Example example = Example.builder(User.class).build();
        example.createCriteria().andEqualTo("username", user.getUsername());
        User res = userMapper.selectOneByExample(example);
        if (res != null) {
            return Result.business("用户名已存在！", Optional.empty());
        }
        // 注册
        userMapper.insertSelective(user);

        log.info("用户注册完成");
        return Result.success("注册成功", Optional.empty());
    }

    /**
     * 登录校验
     *
     * @param loginSessionId session id
     *
     * @return 统一响应对象 {@link Result}
     */
    @Override
    public Result checkLogin(String loginSessionId) {
        if (StringUtils.isEmpty(loginSessionId)) {
            return Result.business("loginSessionId不能为空!", Optional.empty());
        }

        String key = prefix + loginSessionId;
        ValueOperations<String, String> operations = template.opsForValue();
        String userVOJSONString = operations.get(key);
        if (StringUtils.isEmpty(userVOJSONString)) {
            return Result.notLogin("用户未登录!", Optional.empty());
        }
        return Result.success("用户已登录", userVOJSONString);
    }

    /**
     * 登录
     *
     * @param user    接收用户名密码
     * @param role    角色, TODO RBAC改造
     * @param session HTTP Session管理对象 TODO Spring Session改造
     *
     * @return 统一响应对象 {@link Result}
     */
    @Override
    public Result login(User user, String role, HttpSession session) {

        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return Result.argumentError("用户名或密码不能为空", Optional.empty());
        }

        log.info("登录, 参数 {}, {}", user, role);

        Example example = Example.builder(User.class).build();
        example.createCriteria()
                .andEqualTo("username", user.getUsername())
                .andEqualTo("password", user.getPassword())
                .andEqualTo("role", role)
                .andEqualTo("status", 1);
        user = userMapper.selectOneByExample(example);
        if (user == null || user.getId() == null) {
            log.info("登录失败");
            return Result.business("用户验证失败，请检查用户名和密码是否正确！", Optional.empty());
        }

        // 数据脱敏
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        final String loginSessionId = session.getId();
        userVO.setLoginSessionId(loginSessionId);

        // 登录信息存储redis
        ValueOperations<String, String> operations = template.opsForValue();
        String key = prefix + loginSessionId;
        try {
            operations.set(key, new ObjectMapper().writeValueAsString(userVO), timeout, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("UserVO-JSON转换异常, " + e.getMessage());
        }

        log.info("登录成功, loginSessionId = {}", loginSessionId);
        return Result.success("登录成功", userVO);
    }

    /**
     * 登出
     *
     * @param loginSessionId Session ID
     *
     * @return 统一响应对象 {@link Result}
     */
    @Override
    public Result logout(String loginSessionId) {

        log.info("登出, loginSessionId = {}", loginSessionId);

        String key = prefix + loginSessionId;
        String userVOJSONString = template.opsForValue().get(key);
        if (StringUtils.isEmpty(userVOJSONString)) {
            log.info("登出失败, loginSessionId = {}", loginSessionId);
            return Result.business("登出异常!传入loginSessionId不存在.", Optional.empty());
        }
        template.delete(key);
        log.info("登出成功, loginSessionId = {}", loginSessionId);
        return Result.success("登出成功!", Optional.empty());
    }

    /**
     * 用户名模糊分页查询列表
     * 模糊格式: %username%
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     * @param username 用户名
     *
     * @return 统一响应对象 {@link Result}
     */
    // @Cacheable(value = "user_items")
    @Override
    public Result items(int pageNum, int pageSize, String username) {
        log.info("用户名模糊分页查询列表, pageNum = {}, pageSize = {}, username = {}", pageNum, pageSize, username);

        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(User.class).build();

        // 不同逻辑的条件构造
        if (StringUtils.isEmpty(username)) {
            example.createCriteria()
                    .andNotEqualTo("username", "root")
                    .andNotEqualTo("status", -1);
        } else {
            example.createCriteria()
                    .andNotEqualTo("status", -1)
                    .andNotEqualTo("username", "root")
                    .andLike("username", "%" + username + "%");
        }
        userMapper.selectByExample(example);
        log.info("用户名模糊分页查询列表完成");

        return Result.success("用户列表查询, 参数: " + username, Pager.<User>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    /**
     * ID删除用户
     *
     * @param id 用户ID
     *
     * @return 统一响应对象 {@link Result}
     */
    // @CacheEvict(value = "user_items")
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result removeUser(Integer id) {
        if (id == null || id <= 0) {
            return Result.business("删除用户参数错误", Optional.empty());
        }
        log.info("删除用户, 参数: {}", id);

        User user = userMapper.selectByPrimaryKey(id);
        if (user == null || user.getId() == null) {
            return Result.business("删除用户参数错误", Optional.empty());
        }
        user.setStatus(-1);
        userMapper.updateByPrimaryKey(user);
        log.info("删除用户成功");

        return Result.success("删除用户成功", Optional.empty());
    }

    /**
     * 批量删除用户
     *
     * @param ids 用户ID列表
     *
     * @return 统一响应对象 {@link Result}
     */
    // @CacheEvict(value = "user_items")
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result batchRemoveUser(List<Integer> ids) {
        log.info("批量删除用户, 参数: {}", ids);
        ids.forEach(this::removeUser);
        return Result.success("批量删除成功", Optional.empty());
    }

    /**
     * 更新用户信息
     *
     * @param user 新的用户信息
     *
     * @return 统一响应对象 {@link Result}
     */
    // @CachePut(value = "user_items")
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result update(User user) {
        if (user == null) {
            // 异常
            return Result.business("用户更新数据参数异常!", Optional.empty());
        }
        log.info("用户更新数据, 参数: {}", user);

        Long id = user.getId();
        User dbUser = userMapper.selectByPrimaryKey(id);
        if (dbUser == null) {
            // 异常
            return Result.business("用户更新数据参数异常! id: " + id, Optional.empty());
        }
        user.setPassword(dbUser.getPassword());
        userMapper.updateByPrimaryKey(user);
        log.info("用户更新数据更改成功");

        return Result.success("用户更新数据更改成功", user);
    }
}
