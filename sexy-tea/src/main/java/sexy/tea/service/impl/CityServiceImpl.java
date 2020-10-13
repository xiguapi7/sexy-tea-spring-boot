package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.CityMapper;
import sexy.tea.model.City;
import sexy.tea.model.common.Result;
import sexy.tea.service.CityService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class CityServiceImpl implements CityService {

    private final CityMapper cityMapper;

    @Autowired
    public CityServiceImpl(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
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
        PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(City.class).build();
        example.createCriteria().andEqualTo("status", 1);
        List<City> cityList = cityMapper.selectByExample(example);
        return Result.success(cityList);
    }

    @Override
    public Result findByCityName(String cityName) {
        Example example = Example.builder(City.class).build();
        example.createCriteria()
                .andEqualTo("city", cityName)
                .andEqualTo("status", 1);
        List<City> cityList = cityMapper.selectByExample(example);
        return Result.success(cityList);
    }
}
