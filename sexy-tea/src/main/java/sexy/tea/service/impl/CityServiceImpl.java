package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sexy.tea.mapper.CityMapper;
import sexy.tea.model.City;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.CityService;
import sexy.tea.service.StoreService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    private final StoreService storeService;

    @Autowired
    public CityServiceImpl(CityMapper cityMapper, StoreService storeService) {
        this.cityMapper = cityMapper;
        this.storeService = storeService;
    }

    @Override
    public int updateBatch(List<City> list) {
        return cityMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<City> list) {
        return cityMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<City> list) {
        return cityMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(City record) {
        return cityMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(City record) {
        return cityMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {
        Page<City> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(City.class).build();
        example.createCriteria().andNotEqualTo("status", -1);
        List<City> cityList = cityMapper.selectByExample(example);
        return Result.success("查询城市", Pager.<City>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(cityList)
                .build());
    }

    @Override
    public Result findByCityName(int pageNum, int pageSize, String cityName) {
        return storeService.findByCityName(pageNum, pageSize, cityName);
    }

    @Override
    public Result search(int pageNum, int pageSize, String cityName) {
        if (StringUtils.isEmpty(cityName)) {
            return Result.business("参数错误", Optional.empty());
        }
        Page<City> page = PageHelper.startPage(pageNum, pageSize);
        cityName = "%" + cityName + "%";

        cityMapper.search(cityName);

        Example example = Example.builder(City.class).build();
        example.createCriteria()
                .andNotEqualTo("status", -1)
                .andLike("city", cityName);
        cityMapper.selectByExample(example);

        return Result.success("关键字: " + cityName, Pager.<City>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }
}
