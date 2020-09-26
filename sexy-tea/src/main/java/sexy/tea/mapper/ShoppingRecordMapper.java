package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.ShoppingRecord;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface ShoppingRecordMapper extends tk.mybatis.mapper.common.Mapper<ShoppingRecord> {
    int updateBatch(List<ShoppingRecord> list);

    int updateBatchSelective(List<ShoppingRecord> list);

    int batchInsert(@Param("list") List<ShoppingRecord> list);

    int insertOrUpdate(ShoppingRecord record);

    int insertOrUpdateSelective(ShoppingRecord record);
}