package sexy.tea.service;

import sexy.tea.model.Food;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface FoodService {


    int updateBatch(List<Food> list);

    int updateBatchSelective(List<Food> list);

    int batchInsert(List<Food> list);

    int insertOrUpdate(Food record);

    int insertOrUpdateSelective(Food record);

}
