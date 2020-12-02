package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.ErrorLogMapper;
import sexy.tea.model.ErrorLog;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.ErrorLogService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-11-28 下午 02:42
 * desc:
 */
@Service
public class ErrorLogServiceImpl implements ErrorLogService {

    @Resource
    private ErrorLogMapper errorLogMapper;

    @Override
    public Result find(int pageNum, int pageSize) {
        final Page<ErrorLog> page = PageHelper.startPage(pageNum, pageSize);
        final Example example = Example.builder(ErrorLog.class).build();
        example.setOrderByClause("create_time DESC");
        errorLogMapper.selectByExample(example);
        return Result.success("查询成功", Pager.<ErrorLog>builder()
                .result(page.getResult())
                .total(page.getTotal())
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .build());
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void insertLog(ErrorLog errorLog) {
        errorLogMapper.insert(errorLog);
    }
}
