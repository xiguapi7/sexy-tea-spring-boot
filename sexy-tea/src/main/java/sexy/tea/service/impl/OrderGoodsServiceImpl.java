package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.OrderGoodsMapper;
import sexy.tea.model.OrderGoods;
import sexy.tea.service.OrderGoodsService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {

    private final OrderGoodsMapper orderGoodsMapper;

    @Autowired
    public OrderGoodsServiceImpl(OrderGoodsMapper orderGoodsMapper) {
        this.orderGoodsMapper = orderGoodsMapper;
    }

    @Override
    public int updateBatch(List<OrderGoods> list) {
        return orderGoodsMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<OrderGoods> list) {
        return orderGoodsMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<OrderGoods> list) {
        return orderGoodsMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(OrderGoods record) {
        return orderGoodsMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(OrderGoods record) {
        return orderGoodsMapper.insertOrUpdateSelective(record);
    }

}
