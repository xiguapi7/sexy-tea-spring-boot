package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.FoodMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.Food;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.FoodService;
import sexy.tea.utils.MinioUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
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

    @Value("${minio.prefix}")
    private String prefix;

    @Value("${minio.defaultBucketName}")
    private String defaultBucketName;

    @Override
    public int updateBatch(List<Food> list) {
        return foodMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Food> list) {
        return foodMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Food> list) {
        return foodMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Food record) {
        return foodMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Food record) {
        return foodMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Food.class).build();
        example.createCriteria().andEqualTo("status", 1);

        List<Food> foodList = foodMapper.selectByExample(example);
        return Result.success(foodList);
    }

    @Override
    public Result findByPrimaryKey(Integer primaryKey) {
        Food food = foodMapper.selectByPrimaryKey(primaryKey);
        if (food == null || food.getId() == null) {
            return Result.business("查询失败, id: " + primaryKey);
        }
        return Result.success(food);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Food food) {
        if (food == null) {
            // 异常
            return Result.business("参数异常!");
        }
        if (food.getId() == null || food.getId() <= 0) {
            // 插入数据
            foodMapper.insert(food);
        } else {
            // 更新数据
            foodMapper.updateByPrimaryKeySelective(food);
        }
        return Result.success(food.getFoodId());
    }

    @Override
    public Result uploadImage(MinioDto dto, String foodId) {
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("food_id", foodId)
                .andEqualTo("status", 1);
        Food food = foodMapper.selectOneByExample(example);
        // 校验
        if (food == null) {
            return Result.business("参数错误, foodId: " + foodId);
        }
        // 饮料名称
        String foodName = food.getFoodName();
        // 图片
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, foodName, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + foodName + dto.getSuffix();
        // 更新图片地址
        food.setFoodImage(url);
        foodMapper.updateByPrimaryKey(food);
        return Result.success("图片上传成功, 地址为： " + url);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Integer id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误");
        }
        int row = foodMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row);
    }
}
