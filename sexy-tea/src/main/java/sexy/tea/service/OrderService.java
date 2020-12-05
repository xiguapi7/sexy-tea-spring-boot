package sexy.tea.service;

import sexy.tea.model.Order;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.OrderDto;

/**
 * 订单服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface OrderService {

    Result find(int pageNum, int pageSize);

    Result createOrder(OrderDto orderDto);

    Order findByOrderId(String orderId);

    Result findOrderGoodsByOrderId(String orderId);

    Result deleteByOrderId(String orderId);

    void callbackUpdateOrder(String orderId);

    Result findByUid(Long uid);
}

