package sexy.tea.service;

import sexy.tea.model.SysLog;

import java.util.List;
    /**
 *
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description: 
 */
public interface SysLogService{


    int updateBatch(List<SysLog> list);

    int updateBatchSelective(List<SysLog> list);

    int batchInsert(List<SysLog> list);

    int insertOrUpdate(SysLog record);

    int insertOrUpdateSelective(SysLog record);

}
