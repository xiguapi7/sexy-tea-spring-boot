package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Order;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 12:44 2020-09-29
 * description:
 */
@Mapper
public interface OrderMapper extends tk.mybatis.mapper.common.Mapper<Order> {
    int updateBatch(List<Order> list);

    int updateBatchSelective(List<Order> list);

    int batchInsert(@Param("list") List<Order> list);

    int insertOrUpdate(Order record);

    int insertOrUpdateSelective(Order record);

    void fallbackUpdateOrder(String orderId);

    void deleteByOrderId(String orderId);
}