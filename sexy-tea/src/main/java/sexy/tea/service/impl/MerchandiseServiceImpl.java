package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.MerchandiseMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.MerchandiseService;
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
        PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Merchandise.class).build();
        example.createCriteria().andEqualTo("status", 1);

        List<Merchandise> merchandiseList = merchandiseMapper.selectByExample(example);
        return Result.success(merchandiseList);
    }

    @Override
    public Result findByPrimaryKey(Integer primaryKey) {
        Merchandise merchandise = merchandiseMapper.selectByPrimaryKey(primaryKey);
        if (merchandise == null || primaryKey <= 0) {
            return Result.notFound();
        }
        return Result.success(merchandise);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Merchandise merchandise) {
        if (merchandise == null) {
            // 异常
            return Result.business("参数异常!");
        }
        if (merchandise.getId() == null || merchandise.getId() <= 0) {
            // 插入数据
            merchandiseMapper.insert(merchandise);
        } else {
            // 更新数据
            merchandiseMapper.updateByPrimaryKeySelective(merchandise);
        }
        return Result.success(merchandise.getProductId());
    }

    @Override
    public Result uploadImage(MinioDto dto, String productId) {
        // 根据 product_id 查询实体记录
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("productId", productId)
                .andEqualTo("status", 1);
        Merchandise merchandise = merchandiseMapper.selectOneByExample(example);
        // 校验
        if (merchandise == null) {
            return Result.business("参数错误, productId: " + productId);
        }
        // 精选商品名称
        String productName = merchandise.getProductName();
        // 图片
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, productName, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + productName + dto.getSuffix();
        // 更新图片地址
        merchandise.setProductImage(url);
        merchandiseMapper.updateByPrimaryKey(merchandise);
        return Result.success("图片上传成功, 地址为： " + url);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Integer id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误");
        }
        int row = merchandiseMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row);
    }
}
