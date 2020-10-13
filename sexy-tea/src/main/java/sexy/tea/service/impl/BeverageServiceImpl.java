package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.BeverageMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.common.Result;
import sexy.tea.service.BeverageService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class BeverageServiceImpl implements BeverageService {

    private final BeverageMapper beverageMapper;

    @Autowired
    public BeverageServiceImpl(BeverageMapper beverageMapper) {
        this.beverageMapper = beverageMapper;
    }

    @Override
    public int updateBatch(List<Beverage> list) {
        return beverageMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Beverage> list) {
        return beverageMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Beverage> list) {
        return beverageMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Beverage record) {
        return beverageMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Beverage record) {
        return beverageMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria().andEqualTo("status", 1);

        List<Beverage> beverageList = beverageMapper.selectByExample(example);
        return Result.success(beverageList);
    }

    @Override
    // @Cacheable(value = RedisConstant)
    public Result findByPrimaryKey(Integer primaryKey) {

        Beverage beverage = beverageMapper.selectByPrimaryKey(primaryKey);
        if (beverage == null || primaryKey <= 0) {
            return Result.notFound();
        }
        return Result.success(beverage);
    }
}
