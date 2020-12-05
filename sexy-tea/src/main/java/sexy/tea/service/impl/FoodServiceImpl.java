package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.FoodMapper;
import sexy.tea.model.Food;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.FoodService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 食品服务接口实现类
 * <p>
 * TODO 与饮品等服务合并改造
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class FoodServiceImpl implements FoodService {

    private final FoodMapper foodMapper;

    @Autowired
    public FoodServiceImpl(FoodMapper foodMapper) {
        this.foodMapper = foodMapper;
    }

    @Cacheable(value = "food_items")
    @Override
    public Result find(int pageNum, int pageSize) {
        Page<Food> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Food.class).build();
        example.createCriteria().andNotEqualTo("status", -1);

        List<Food> foodList = foodMapper.selectByExample(example);
        return Result.success("查询食品", Pager.<Food>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(foodList)
                .build());
    }

    @Cacheable(value = "food_id_item")
    @Override
    public Result findByPrimaryKey(Long primaryKey) {
        Food food = foodMapper.selectByPrimaryKey(primaryKey);
        if (food == null || food.getId() == null) {
            return Result.business("查询失败, id: " + primaryKey, Optional.empty());
        }
        return Result.success("主键: " + primaryKey, food);
    }

    @CachePut(value = {"food_menu_items", "food_name_items", "food_id_item", "food_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Food food) {
        if (food == null) {
            // 异常
            return Result.business("参数异常!", Optional.empty());
        }
        if (food.getId() == null || food.getId() <= 0) {
            // 插入数据
            food.setFoodId(UUID.randomUUID().toString().replace("-", ""));
            foodMapper.insert(food);
        } else {
            // 更新数据
            foodMapper.updateByPrimaryKeySelective(food);
        }
        return Result.success("更改成功", food.getFoodId());
    }

    @CacheEvict(value = {"food_menu_items", "food_name_items", "food_id_item", "food_items"})
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Long id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误", Optional.empty());
        }
        int row = foodMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row, Optional.empty());
    }

    @Cacheable(value = "food_name_items")
    @Override
    public Result findByName(String name, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return Result.business("参数错误", Optional.empty());
        }
        name = "%" + name + "%";
        Page<Food> page = PageHelper.startPage(pageNum, pageSize);
        List<Food> foodList = foodMapper.findByName(name);
        if (foodList == null) {
            return Result.business("查询的食品不存在", Optional.empty());
        }
        return Result.success("关键词: " + name, Pager.<Food>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(foodList)
                .build());
    }

    @Cacheable(value = "food_menu_items")
    @Override
    public Result itemsMenu(int pageNum, int pageSize) {
        Page<Food> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Food.class).build();
        example.createCriteria().andEqualTo("status", 1);
        List<Food> foodList = foodMapper.selectByExample(example);
        return Result.success("查询食品", Pager.<Food>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(foodList)
                .build());
    }
}
