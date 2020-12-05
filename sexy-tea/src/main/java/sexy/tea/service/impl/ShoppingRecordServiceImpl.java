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
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.ShoppingRecordMapper;
import sexy.tea.model.ShoppingRecord;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.ShoppingRecordService;
import tk.mybatis.mapper.entity.Example;

import java.util.Optional;

/**
 * 购物车服务接口实现类
 *
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class ShoppingRecordServiceImpl implements ShoppingRecordService {

    private final ShoppingRecordMapper shoppingRecordMapper;

    @Autowired
    public ShoppingRecordServiceImpl(ShoppingRecordMapper shoppingRecordMapper) {
        this.shoppingRecordMapper = shoppingRecordMapper;
    }

    /**
     * 分页查询购物车
     *
     * @param pageNum  当前页
     * @param pageSize 条数
     *
     * @return 结果集
     */
    @Cacheable(value = "shopping_items")
    @Override
    public Result find(int pageNum, int pageSize) {
        Page<ShoppingRecord> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(ShoppingRecord.class).build();
        example.createCriteria().andNotEqualTo("status", -1);
        shoppingRecordMapper.selectByExample(example);
        return Result.success("查询成功", Pager.<ShoppingRecord>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }

    /**
     * 按用户ID查询购物车
     *
     * @param uid 用户ID
     *
     * @return 结果集
     */
    @Override
    public Result findByUid(Long uid) {
        if (uid == null || uid <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        // 根据uid和status <> -1查询购物车记录
        Example example = Example.builder(ShoppingRecord.class).build();
        example.createCriteria()
                .andNotEqualTo("status", -1)
                .andEqualTo("uid", uid);
        example.setOrderByClause("update_time DESC");

        final ShoppingRecord shoppingRecord = shoppingRecordMapper.selectByExample(example).get(0);

        if (shoppingRecord == null) {
            return Result.success("查询的用户id无购物车项", uid);
        }

        return Result.success("查询成功", shoppingRecord);
    }

    /**
     * 存储或更改购物车信息
     *
     * @param record 购物车信息
     *
     * @return 响应
     */
    @CachePut(value = "shopping_items")
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(ShoppingRecord record) {
        if (record == null || record.getUid() == null) {
            return Result.business("参数错误", Optional.empty());
        }

        final Example example = Example.builder(ShoppingRecord.class).build();
        example.createCriteria().andEqualTo("status", 1).andEqualTo("uid", record.getUid());
        ShoppingRecord newShoppingRecord = shoppingRecordMapper.selectOneByExample(example);

        if (newShoppingRecord == null) {
            newShoppingRecord = ShoppingRecord.builder().build();
        }

        newShoppingRecord.setUid(record.getUid());
        newShoppingRecord.setItems(record.getItems());
        newShoppingRecord.setTotal(record.getTotal());

        if (newShoppingRecord.getId() == null) {
            shoppingRecordMapper.insertSelective(newShoppingRecord);
        } else {
            shoppingRecordMapper.updateByPrimaryKeySelective(newShoppingRecord);
        }

        return Result.success("更改成功", newShoppingRecord);
    }

    /**
     * 删除购物车项
     *
     * @param uid 用户ID
     *
     * @return 响应
     */
    @CacheEvict(value = "shopping_items")
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result delete(Long uid) {
        if (uid == null || uid <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        log.info("清空购物车: uid = {}", uid);
        // 根据uid清空购物车
        shoppingRecordMapper.deleteByUid(uid);
        return Result.success("清空购物车成功: uid = " + uid, Optional.empty());
    }

    /**
     * 更新购物车状态
     *
     * @param id 用户ID
     */
    @CachePut(value = "shopping_items")
    @Override
    public void updateShoppingRecordByUid(Long id) {
        shoppingRecordMapper.updateShoppingRecordByUid(id);
    }
}
