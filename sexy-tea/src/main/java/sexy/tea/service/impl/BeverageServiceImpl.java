package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.BeverageMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.BeverageService;
import sexy.tea.utils.MinioUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        Page<Beverage> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria().andNotEqualTo("status", -1);

        beverageMapper.selectByExample(example);

        return Result.success("饮品查询", Pager.<Beverage>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    @Override
    public Result itemsMenu(int type, int pageNum, int pageSize) {
        Page<Beverage> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("type", type)
                .andEqualTo("status", 1);

        beverageMapper.selectByExample(example);

        return Result.success("饮品查询", Pager.<Beverage>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    @Override
    // @Cacheable(value = RedisConstant)
    public Result findByPrimaryKey(Long primaryKey) {

        Beverage beverage = beverageMapper.selectByPrimaryKey(primaryKey);
        if (beverage == null || primaryKey <= 0) {
            return Result.notFound();
        }
        return Result.success("主键查询饮品", beverage);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Beverage beverage) {
        if (beverage == null) {
            // 异常
            return Result.business("参数异常!", Optional.empty());
        }
        if (beverage.getId() == null || beverage.getId() <= 0) {
            // 插入数据
            String beverageId = UUID.randomUUID().toString().replace("-", "");
            // 生成随机的饮品ID
            beverage.setBeverageId(beverageId);
            beverageMapper.insertSelective(beverage);
        } else {
            // 更新数据
            beverageMapper.updateByPrimaryKeySelective(beverage);
        }
        return Result.success("更改成功", beverage);
    }

    @Override
    public Result uploadImage(MinioDto dto, Long id) {
        // 根据 beverage_id 查询实体记录
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("id", id)
                .andEqualTo("status", 1);
        Beverage beverage = beverageMapper.selectOneByExample(example);
        // 校验
        if (beverage == null) {
            return Result.business("参数错误, id: " + id, Optional.empty());
        }
        String name = beverage.getBeverageId() + dto.getSuffix();
        // 图片
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, name, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + name;
        // 更新图片地址
        beverage.setBeverageImage(url);
        beverageMapper.updateByPrimaryKey(beverage);
        return Result.success("图片上传成功, 地址为： " + url, Optional.empty());
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Long id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误", Optional.empty());
        }
        Beverage beverage = beverageMapper.selectByPrimaryKey(id);
        if (beverage == null || beverage.getId() <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        beverage.setStatus(-1);
        beverageMapper.updateByPrimaryKey(beverage);
        return Result.success("删除成功", Optional.empty());
    }

    @Override
    public Result findByName(String name, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return Result.business("参数错误", Optional.empty());
        }
        name = "%" + name + "%";
        Page<Beverage> page = PageHelper.startPage(pageNum, pageSize);
        List<Beverage> beverage = beverageMapper.findByName(name);
        if (beverage == null) {
            return Result.business("查询的饮品不存在", Optional.empty());
        }
        return Result.success("关键词: " + name, Pager.<Beverage>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(beverage)
                .build());
    }
}
