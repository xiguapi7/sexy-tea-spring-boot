package sexy.tea.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sexy.tea.mapper.UserMapper;
import sexy.tea.model.User;
import sexy.tea.model.common.Result;
import sexy.tea.service.UserService;
import sexy.tea.vo.UserVO;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    private StringRedisTemplate template;

    @Value("${login.session.prefix}")
    private String prefix;

    @Value("${login.session.timeout}")
    private long timeout;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int updateBatch(List<User> list) {
        return userMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<User> list) {
        return userMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(User record) {
        return userMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(User record) {
        return userMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result register(User user) {

        // md5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        user.setRole("user");

        Example example = Example.builder(User.class).build();
        example.createCriteria().andEqualTo("username", user.getUsername());
        User res = userMapper.selectOneByExample(example);
        if (res != null) {
            return Result.business("用户名已存在！");
        }
        // 注册
        userMapper.insertSelective(user);
        return Result.success("注册成功");
    }

    @Override
    public Result checkLogin(String loginSessionId) {
        if (StringUtils.isEmpty(loginSessionId)) {
            return Result.business("loginSessionId不能为空!");
        }

        String key = prefix + loginSessionId;
        ValueOperations<String, String> operations = template.opsForValue();
        String userVOJSONString = operations.get(key);
        if (StringUtils.isEmpty(userVOJSONString)) {
            return Result.notLogin("用户未登录!");
        }
        return Result.success(userVOJSONString);
    }

    @Override
    public Result login(User user, String role, HttpSession session) {

        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return Result.argumentError("用户名或密码不能为空");
        }

        Example example = Example.builder(User.class).build();
        example.createCriteria()
                .andEqualTo("username", user.getUsername())
                .andEqualTo("password", user.getPassword())
                .andEqualTo("role", role)
                .andEqualTo("status", 1);
        user = userMapper.selectOneByExample(example);
        if (user.getId() == null) {
            return Result.business("用户验证失败，请检查用户名和密码是否正确！");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setLoginSessionId(session.getId());

        // 登录信息存储redis
        ValueOperations<String, String> operations = template.opsForValue();
        String key = prefix + session.getId();
        try {
            operations.set(key, new ObjectMapper().writeValueAsString(userVO), timeout, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("UserVO-JSON转换异常, " + e.getMessage());
        }

        return Result.success(userVO);
    }

    @Override
    public Result logout(String loginSessionId) {

        String key = prefix + loginSessionId;
        ValueOperations<String, String> operations = template.opsForValue();
        String userVOJSONString = operations.get(key);
        if (StringUtils.isEmpty(userVOJSONString)) {
            return Result.business("登出异常!传入loginSessionId不存在.");
        }
        template.delete(key);
        return Result.success("登出成功!");
    }
}
