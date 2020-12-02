package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void insertLog(LoginLog loginLog) {
        if (loginLog != null) {
            log.info("登录日志记录: {}", loginLog);
            loginLogMapper.insert(loginLog);
        }
    }
}
