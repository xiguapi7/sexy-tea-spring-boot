package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.FoodMapper;
import sexy.tea.model.Food;
import sexy.tea.service.FoodService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class FoodServiceImpl implements FoodService {

    private final FoodMapper foodMapper;

    @Autowired
    public FoodServiceImpl(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Override
    public int updateBatch(List<Food> list) {
        return foodMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Food> list) {
        return foodMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Food> list) {
        return foodMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Food record) {
        return foodMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Food record) {
        return foodMapper.insertOrUpdateSelective(record);
    }

}
