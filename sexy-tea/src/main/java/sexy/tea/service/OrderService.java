package sexy.tea.service;

import sexy.tea.common.Result;
import sexy.tea.model.Order;
import sexy.tea.model.OrderGoods;
import sexy.tea.model.OrderShipping;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface OrderService {

    int updateBatch(List<Order> list);

    int updateBatchSelective(List<Order> list);

    int batchInsert(List<Order> list);

    int insertOrUpdate(Order record);

    int insertOrUpdateSelective(Order record);

    Result createOrder(Order order, List<OrderGoods> orderGoodsList, OrderShipping orderShipping);
}

