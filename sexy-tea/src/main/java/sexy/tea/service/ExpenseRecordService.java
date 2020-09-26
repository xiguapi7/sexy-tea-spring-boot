package sexy.tea.service;

import sexy.tea.model.ExpenseRecord;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface ExpenseRecordService {


    int updateBatch(List<ExpenseRecord> list);

    int updateBatchSelective(List<ExpenseRecord> list);

    int batchInsert(List<ExpenseRecord> list);

    int insertOrUpdate(ExpenseRecord record);

    int insertOrUpdateSelective(ExpenseRecord record);

}
