package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sexy.tea.mapper.StoreMapper;
import sexy.tea.model.Store;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.StoreService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * 商店服务接口实现类
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    /**
     * 分页查询商店
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 统一响应对象 {@link Result}
     */
    // @Cacheable(value = "store_items")
    @Override
    public Result find(int pageNum, int pageSize) {
        log.info("分页查询商店, pageNum = {}, pageSize = {}", pageNum, pageSize);

        Page<Store> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Store.class).build();
        example.createCriteria().andEqualTo("status", 1);
        List<Store> storeList = storeMapper.selectByExample(example);
        return Result.success("列表查询", Pager.<Store>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(storeList)
                .build());
    }

    /**
     * 按城市名称模糊分页查询商店
     * 模糊条件: %cityName%
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     * @param cityName 城市名称
     *
     * @return 统一响应对象 {@link Result}
     */
    // @Cacheable(value = "store_items_cityName")
    @Override
    public Result findByCityName(int pageNum, int pageSize, String cityName) {
        if (StringUtils.isEmpty(cityName)) {
            return Result.business("参数错误, cityName: " + cityName, Optional.empty());
        }
        cityName = "%" + cityName + "%";
        Page<Store> page = PageHelper.startPage(pageNum, pageSize);
        storeMapper.findByCity(cityName);
        return Result.success("关键词:" + cityName, Pager.<Store>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }
}
