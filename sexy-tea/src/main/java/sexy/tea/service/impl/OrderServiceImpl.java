package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import sexy.tea.common.Result;
import sexy.tea.mapper.OrderGoodsMapper;
import sexy.tea.mapper.OrderMapper;
import sexy.tea.mapper.OrderShippingMapper;
import sexy.tea.model.Order;
import sexy.tea.model.OrderGoods;
import sexy.tea.model.OrderShipping;
import sexy.tea.service.OrderService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    private final OrderShippingMapper orderShippingMapper;

    private final OrderGoodsMapper orderGoodsMapper;

    // @Value("${ord.gen}")
    // private String genKey;
    //
    // @Value("${ord.default")
    // private String defaultKey;
    //
    // @Value("${ord.item}")
    // private String itemKey;

    private final StringRedisTemplate template;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderShippingMapper orderShippingMapper, OrderGoodsMapper orderGoodsMapper, StringRedisTemplate template) {
        this.orderMapper = orderMapper;
        this.orderShippingMapper = orderShippingMapper;
        this.orderGoodsMapper = orderGoodsMapper;
        this.template = template;
    }

    @Override
    public int updateBatch(List<Order> list) {
        return orderMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Order> list) {
        return orderMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Order> list) {
        return orderMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Order record) {
        return orderMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Order record) {
        return orderMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result createOrder(Order order, List<OrderGoods> orderGoodsList, OrderShipping orderShipping) {

        // TODO 订单生成
        ValueOperations<String, String> valueOperations = template.opsForValue();
        // String gen = valueOperations.get(genKey);
        

        return null;
    }
}

