package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.SysLogMapper;
import sexy.tea.model.SysLog;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.SysLogService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description:
 */
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public Result find(int pageNum, int pageSize) {
        final Page<SysLog> page = PageHelper.startPage(pageNum, pageSize);
        final Example example = Example.builder(SysLog.class).build();
        example.setOrderByClause("create_time DESC");
        sysLogMapper.selectByExample(example);
        return Result.success("查询成功", Pager.<SysLog>builder()
                .result(page.getResult())
                .total(page.getTotal())
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .build());
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void insertLog(SysLog sysLog) {
        if (sysLog == null) {
            log.info("日志记录异常.");
        }
        sysLogMapper.insert(sysLog);
    }
}

