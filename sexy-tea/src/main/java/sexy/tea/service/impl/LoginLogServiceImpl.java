package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.LoginLogMapper;
import sexy.tea.model.LoginLog;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.LoginLogService;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:57
 * desc:
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public Result find(int pageNum, int pageSize) {
        final Page<LoginLog> page = PageHelper.startPage(pageNum, pageSize);
        loginLogMapper.selectAll();
        return Result.success("查询成功", Pager.<LoginLog>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }
}
