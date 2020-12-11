package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.*;
import sexy.tea.model.Dashboard;
import sexy.tea.model.common.Result;
import sexy.tea.model.vo.OrderVO;
import sexy.tea.service.StatisticsService;

import java.util.List;

/**
 * 统计服务接口实现类
 *
 * <p>
 *
 * @author 大大大西西瓜皮🍉
 * @since 2020-12-5 下午 12:03
 * desc:
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final BeverageMapper beverageMapper;

    private final FoodMapper foodMapper;

    private final SelectionMapper selectionMapper;

    private final StoreMapper storeMapper;

    private final UserMapper userMapper;

    private final CityMapper cityMapper;

    private final OrderMapper orderMapper;


    @Autowired
    public StatisticsServiceImpl(BeverageMapper beverageMapper,
                                 FoodMapper foodMapper,
                                 SelectionMapper selectionMapper,
                                 StoreMapper storeMapper,
                                 UserMapper userMapper,
                                 CityMapper cityMapper, OrderMapper orderMapper) {
        this.beverageMapper = beverageMapper;
        this.foodMapper = foodMapper;
        this.selectionMapper = selectionMapper;
        this.storeMapper = storeMapper;
        this.userMapper = userMapper;
        this.cityMapper = cityMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public Result getDashboardInfo() {

        final Long userCount = userMapper.userCount();
        final Long storeCount = storeMapper.storeCount();
        final Double purchases = orderMapper.purchases();
        final Long foodCount = foodMapper.foodCount();
        final Long selectionCount = selectionMapper.selectionCount();
        final Long beverageCount = beverageMapper.beverageCount();
        final List<OrderVO> orderVOList = orderMapper.orderVOList();
        // final List<CityVO> cityVOList = cityMapper.cityVOList();

        return Result.success("统计完成", Dashboard.builder()
                .userCount(userCount)
                .storeCount(storeCount)
                .purchases(purchases)
                .foodCount(foodCount)
                .selectionCount(selectionCount)
                .beverageCount(beverageCount)
                .orderVOList(orderVOList)
                // .cityVOList(cityVOList)
                .build());
    }
}
