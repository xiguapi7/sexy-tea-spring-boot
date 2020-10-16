package sexy.tea.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.SysLogMapper;
import sexy.tea.model.SysLog;
import sexy.tea.service.SysLogService;

import javax.annotation.Resource;
import java.util.List;
/**
 *
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description: 
 */
@Service
@Slf4j
public class SysLogServiceImpl implements SysLogService{

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public int updateBatch(List<SysLog> list) {
        return sysLogMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<SysLog> list) {
        return sysLogMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<SysLog> list) {
        return sysLogMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(SysLog record) {
        return sysLogMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(SysLog record) {
        return sysLogMapper.insertOrUpdateSelective(record);
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
