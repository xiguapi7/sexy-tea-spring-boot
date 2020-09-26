package sexy.tea.service;

import sexy.tea.model.ShoppingRecord;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface ShoppingRecordService {


    int updateBatch(List<ShoppingRecord> list);

    int updateBatchSelective(List<ShoppingRecord> list);

    int batchInsert(List<ShoppingRecord> list);

    int insertOrUpdate(ShoppingRecord record);

    int insertOrUpdateSelective(ShoppingRecord record);

}
