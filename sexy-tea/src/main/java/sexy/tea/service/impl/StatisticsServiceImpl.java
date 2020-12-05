package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.*;

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
public class StatisticsServiceImpl {

    private final BeverageMapper beverageMapper;

    private final FoodMapper foodMapper;

    private final SelectionMapper selectionMapper;

    private final StoreMapper storeMapper;

    private final UserMapper userMapper;

    private final CityMapper cityMapper;


    @Autowired
    public StatisticsServiceImpl(BeverageMapper beverageMapper,
                                 FoodMapper foodMapper,
                                 SelectionMapper selectionMapper,
                                 StoreMapper storeMapper,
                                 UserMapper userMapper,
                                 CityMapper cityMapper) {
        this.beverageMapper = beverageMapper;
        this.foodMapper = foodMapper;
        this.selectionMapper = selectionMapper;
        this.storeMapper = storeMapper;
        this.userMapper = userMapper;
        this.cityMapper = cityMapper;
    }
}
