package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.OrderGoods;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 12:44 2020-09-29
 * description:
 */
@Mapper
public interface OrderGoodsMapper extends tk.mybatis.mapper.common.Mapper<OrderGoods> {
    int updateBatch(List<OrderGoods> list);

    int updateBatchSelective(List<OrderGoods> list);

    int batchInsert(@Param("list") List<OrderGoods> list);

    int insertOrUpdate(OrderGoods record);

    int insertOrUpdateSelective(OrderGoods record);

    void deleteByOrderId(String orderId);
}