package sexy.tea.service;

import sexy.tea.model.OrderGoods;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface OrderGoodsService {


    int updateBatch(List<OrderGoods> list);

    int updateBatchSelective(List<OrderGoods> list);

    int batchInsert(List<OrderGoods> list);

    int insertOrUpdate(OrderGoods record);

    int insertOrUpdateSelective(OrderGoods record);

}
