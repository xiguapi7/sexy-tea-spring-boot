package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.SelectionMapper;
import sexy.tea.model.Beverage;
import sexy.tea.model.Selection;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;
import sexy.tea.service.SelectionService;
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
public class SelectionServiceImpl implements SelectionService {

    private final SelectionMapper selectionMapper;

    @Autowired
    public SelectionServiceImpl(SelectionMapper selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

    @Value("${minio.prefix}")
    private String prefix;

    @Value("${minio.defaultBucketName}")
    private String defaultBucketName;

    @Override
    public int updateBatch(List<Selection> list) {
        return selectionMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Selection> list) {
        return selectionMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Selection> list) {
        return selectionMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Selection record) {
        return selectionMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Selection record) {
        return selectionMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Selection.class).build();
        example.createCriteria().andEqualTo("status", 1);

        List<Selection> selectionList = selectionMapper.selectByExample(example);
        return Result.success(selectionList);
    }

    @Override
    public Result findByPrimaryKey(Integer primaryKey) {
        Selection selection = selectionMapper.selectByPrimaryKey(primaryKey);
        if (selection == null || primaryKey <= 0) {
            return Result.notFound();
        }
        return Result.success(selection);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Selection selection) {
        if (selection == null) {
            // 异常
            return Result.business("参数异常!");
        }
        if (selection.getId() == null || selection.getId() <= 0) {
            // 插入数据
            selectionMapper.insert(selection);
        } else {
            // 更新数据
            selectionMapper.updateByPrimaryKeySelective(selection);
        }
        return Result.success(selection.getProductId());
    }

    @Override
    public Result uploadImage(MinioDto dto, String productId) {
        // 根据 product_id 查询实体记录
        Example example = Example.builder(Beverage.class).build();
        example.createCriteria()
                .andEqualTo("productId", productId)
                .andEqualTo("status", 1);
        Selection selection = selectionMapper.selectOneByExample(example);
        // 校验
        if (selection == null) {
            return Result.business("参数错误, productId: " + productId);
        }
        // 精选商品名称
        String productName = selection.getProductName();
        // 图片
        try {
            InputStream is = dto.getFile().getInputStream();
            MinioUtils.upload(defaultBucketName, productName, is, dto.getContentType());
        } catch (IOException e) {
            log.error("上传失败, 错误信息：{}", e.getMessage());
        }
        String url = prefix + productName + dto.getSuffix();
        // 更新图片地址
        selection.setProductImage(url);
        selectionMapper.updateByPrimaryKey(selection);
        return Result.success("图片上传成功, 地址为： " + url);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Integer id) {
        if (id == null || id <= 0) {
            // 校验
            return Result.business("参数错误");
        }
        int row = selectionMapper.deleteByPrimaryKey(id);
        return Result.success("删除成功, 受影响的行数: " + row);
    }
}
