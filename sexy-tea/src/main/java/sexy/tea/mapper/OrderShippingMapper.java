package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.OrderShipping;

import java.util.List;

/**
 *
 * author 大大大西西瓜皮🍉
 * date 12:44 2020-09-29
 * description: 
 */
@Mapper
public interface OrderShippingMapper extends tk.mybatis.mapper.common.Mapper<OrderShipping> {
    int updateBatch(List<OrderShipping> list);

    int updateBatchSelective(List<OrderShipping> list);

    int batchInsert(@Param("list") List<OrderShipping> list);

    int insertOrUpdate(OrderShipping record);

    int insertOrUpdateSelective(OrderShipping record);
}