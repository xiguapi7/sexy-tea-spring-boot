package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.ExpenseRecordMapper;
import sexy.tea.service.ExpenseRecordService;

/**
 * 消费服务接口实现类
 * <p>
 * TODO 可能优化掉
 * <p>
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

}
