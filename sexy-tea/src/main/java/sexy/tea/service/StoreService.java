package sexy.tea.service;

import sexy.tea.common.Result;
import sexy.tea.model.Store;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface StoreService {


    int updateBatch(List<Store> list);

    int updateBatchSelective(List<Store> list);

    int batchInsert(List<Store> list);

    int insertOrUpdate(Store record);

    int insertOrUpdateSelective(Store record);

    Result find();
}
