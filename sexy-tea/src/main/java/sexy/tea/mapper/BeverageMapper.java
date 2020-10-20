package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Beverage;

import java.util.List;

/**
 *
 *
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface BeverageMapper extends tk.mybatis.mapper.common.Mapper<Beverage> {
    int updateBatch(List<Beverage> list);

    int updateBatchSelective(List<Beverage> list);

    int batchInsert(@Param("list") List<Beverage> list);

    int insertOrUpdate(Beverage record);

    int insertOrUpdateSelective(Beverage record);

    List<Beverage> findByName(String name);
}