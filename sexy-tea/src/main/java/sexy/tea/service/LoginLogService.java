package sexy.tea.service;

import sexy.tea.model.LoginLog;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface LoginLogService {


    int updateBatch(List<LoginLog> list);

    int updateBatchSelective(List<LoginLog> list);

    int batchInsert(List<LoginLog> list);

    int insertOrUpdate(LoginLog record);

    int insertOrUpdateSelective(LoginLog record);

}
