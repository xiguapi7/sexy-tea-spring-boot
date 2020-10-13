package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.BeverageMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.BeverageService;
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
public class BeverageServiceImpl implements BeverageService {

    private final BeverageMapper beverageMapper;

    @Autowired
    public BeverageServiceImpl(BeverageMapper beverageMapper) {
        this.beverageMapper = beverageMapper;
    }

    @Value("${minio.prefix}")
    private String prefix;

    @Value("${minio.defaultBucketName}")
    private String defaultBucketName;

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

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Beverage beverage) {
        if (beverage == null) {
            // 异常
            return Result.business("参数异常!");
        }
        if (beverage.getId() == null || beverage.getId() <= 0) {
            // 插入数据
            beverageMapper.insert(beverage);
        } else {
            // 更新数据
            beverageMapper.updateByPrimaryKeySelective(beverage);
        }
        return Result.success(beverage.getBeverageId());
    }

    @Override
    public Result uploadImage(MinioDto dto, String beverageId) {
        // 根据 beverage_id 查询实体记录
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("beverage_id", beverageId)
                .andEqualTo("status", 1);
        Beverage beverage = beverageMapper.selectOneByExample(example);
        // 校验
        if (beverage == null) {
            return Result.business("参数错误, beverageId: " + beverageId);
        }
        // 饮料名称
        String beverageName = beverage.getBeverageName();
        // 图片
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, beverageName, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + beverageName + dto.getSuffix();
        // 更新图片地址
        beverage.setBeverageImage(url);
        beverageMapper.updateByPrimaryKey(beverage);
        return Result.success("图片上传成功, 地址为： " + url);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Integer id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误");
        }
        int row = beverageMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row);
    }
}
