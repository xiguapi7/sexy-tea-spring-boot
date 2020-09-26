package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.ShoppingRecordMapper;
import sexy.tea.model.ShoppingRecord;
import sexy.tea.service.ShoppingRecordService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class ShoppingRecordServiceImpl implements ShoppingRecordService {

    private final ShoppingRecordMapper shoppingRecordMapper;

    @Autowired
    public ShoppingRecordServiceImpl(ShoppingRecordMapper shoppingRecordMapper) {
        this.shoppingRecordMapper = shoppingRecordMapper;
    }

    @Override
    public int updateBatch(List<ShoppingRecord> list) {
        return shoppingRecordMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<ShoppingRecord> list) {
        return shoppingRecordMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ShoppingRecord> list) {
        return shoppingRecordMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(ShoppingRecord record) {
        return shoppingRecordMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(ShoppingRecord record) {
        return shoppingRecordMapper.insertOrUpdateSelective(record);
    }

}
