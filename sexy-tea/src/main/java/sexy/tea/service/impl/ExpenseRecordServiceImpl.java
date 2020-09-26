package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.ExpenseRecordMapper;
import sexy.tea.model.ExpenseRecord;
import sexy.tea.service.ExpenseRecordService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class ExpenseRecordServiceImpl implements ExpenseRecordService {

    private final ExpenseRecordMapper expenseRecordMapper;

    @Autowired
    public ExpenseRecordServiceImpl(ExpenseRecordMapper expenseRecordMapper) {
        this.expenseRecordMapper = expenseRecordMapper;
    }

    @Override
    public int updateBatch(List<ExpenseRecord> list) {
        return expenseRecordMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<ExpenseRecord> list) {
        return expenseRecordMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ExpenseRecord> list) {
        return expenseRecordMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(ExpenseRecord record) {
        return expenseRecordMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(ExpenseRecord record) {
        return expenseRecordMapper.insertOrUpdateSelective(record);
    }

}
