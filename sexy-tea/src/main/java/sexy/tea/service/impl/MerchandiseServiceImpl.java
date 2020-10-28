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
import sexy.tea.mapper.MerchandiseMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.MerchandiseService;
import sexy.tea.utils.MinioUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * 商品接口实现类
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class MerchandiseServiceImpl implements MerchandiseService {

    private final MerchandiseMapper merchandiseMapper;

    @Autowired
    public MerchandiseServiceImpl(MerchandiseMapper merchandiseMapper) {
        this.merchandiseMapper = merchandiseMapper;
    }

    @Value("${minio.prefix}")
    private String prefix;

    @Value("${minio.defaultBucketName}")
    private String defaultBucketName;

    @Override
    public int updateBatch(List<Merchandise> list) {
        return merchandiseMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Merchandise> list) {
        return merchandiseMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Merchandise> list) {
        return merchandiseMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Merchandise record) {
        return merchandiseMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Merchandise record) {
        return merchandiseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {
        Page<Merchandise> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Merchandise.class).build();
        example.createCriteria().andEqualTo("status", 1);

        List<Merchandise> merchandiseList = merchandiseMapper.selectByExample(example);
        return Result.success("查询商品", Pager.<Merchandise>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(merchandiseList)
                .build());
    }

    @Override
    public Result findByPrimaryKey(Integer primaryKey) {
        Merchandise merchandise = merchandiseMapper.selectByPrimaryKey(primaryKey);
        if (merchandise == null || primaryKey <= 0) {
            return Result.notFound();
        }
        return Result.success("主键: " + primaryKey, merchandise);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Merchandise merchandise) {
        if (merchandise == null) {
            // 异常
            return Result.business("参数异常!", Optional.empty());
        }
        if (merchandise.getId() == null || merchandise.getId() <= 0) {
            // 插入数据
            merchandiseMapper.insert(merchandise);
        } else {
            // 更新数据
            merchandiseMapper.updateByPrimaryKeySelective(merchandise);
        }
        return Result.success("更改成功", merchandise.getProductId());
    }

    @Override
    public Result uploadImage(MinioDto dto, Long id) {
        // 根据 product_id 查询实体记录
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("id", id)
                .andEqualTo("status", 1);
        Merchandise merchandise = merchandiseMapper.selectOneByExample(example);
        // 校验
        if (merchandise == null) {
            return Result.business("参数错误, id: " + id, Optional.empty());
        }
        String name = merchandise.getProductId() + dto.getSuffix();
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, name, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + name;
        // 更新图片地址
        merchandise.setProductImage(url);
        merchandiseMapper.updateByPrimaryKey(merchandise);
        return Result.success("图片上传成功, 地址为： " + url, Optional.empty());
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Integer id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误", Optional.empty());
        }
        int row = merchandiseMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row, Optional.empty());
    }

    @Override
    public Result findByName(String name, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(name)) {
            return Result.business("参数错误", Optional.empty());
        }
        name = "%" + name + "%";
        Page<Merchandise> page = PageHelper.startPage(pageNum, pageSize);
        List<Merchandise> merchandiseList = merchandiseMapper.findByName(name);
        if (merchandiseList == null) {
            return Result.business("查询的食品不存在", Optional.empty());
        }
        return Result.success("关键词:" + name, Pager.<Merchandise>builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .total(page.getTotal())
                .result(merchandiseList)
                .build());
    }
}
