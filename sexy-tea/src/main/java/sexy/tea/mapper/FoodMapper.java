package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Food;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface FoodMapper extends tk.mybatis.mapper.common.Mapper<Food> {
    int updateBatch(List<Food> list);

    int updateBatchSelective(List<Food> list);

    int batchInsert(@Param("list") List<Food> list);

    int insertOrUpdate(Food record);

    int insertOrUpdateSelective(Food record);

    List<Food> findByName(String name);

    Long foodCount();
}