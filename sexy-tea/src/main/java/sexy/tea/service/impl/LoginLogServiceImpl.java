package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.LoginLogMapper;
import sexy.tea.model.LoginLog;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.LoginLogService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 登录日志服务接口实现类
 * <p>
 * TODO 日志服务改造
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:57
 * desc:
 */
@Service
@Slf4j
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    /**
     * 分页查询
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    @Cacheable(value = "login_log_items")
    @Override
    public Result find(int pageNum, int pageSize) {
        final Page<LoginLog> page = PageHelper.startPage(pageNum, pageSize);
        final Example example = Example.builder(LoginLog.class).build();
        example.setOrderByClause("create_time DESC");
        loginLogMapper.selectByExample(example);
        return Result.success("查询成功", Pager.<LoginLog>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    /**
     * 保存
     *
     * @param loginLog 日志数据
     */
    @CachePut(value = {"login_log_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void insertLog(LoginLog loginLog) {
        if (loginLog != null) {
            log.info("登录日志记录: {}", loginLog);
            loginLogMapper.insert(loginLog);
        }
    }
}
