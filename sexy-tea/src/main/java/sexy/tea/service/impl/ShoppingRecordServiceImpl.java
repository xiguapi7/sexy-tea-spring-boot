package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.ShoppingRecordMapper;
import sexy.tea.model.ShoppingRecord;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.shopping.*;
import sexy.tea.service.ShoppingRecordService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 购物车服务接口实现类
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
        ShoppingRecord shoppingRecord = shoppingRecordMapper.selectOneByExample(example);
        return Result.success("查询成功", shoppingRecord);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(ShoppingRecordDto dto) {

        if (dto == null) {
            return Result.business("参数错误", Optional.empty());
        }

        Long uid = dto.getUid();

        if (uid == null || uid <= 0) {
            return Result.business("参数错误", Optional.empty());
        }

        Example example = Example.builder(ShoppingRecord.class).build();
        example.createCriteria().andNotEqualTo("status", -1).andEqualTo("uid", uid);
        ShoppingRecord record = shoppingRecordMapper.selectOneByExample(example);

        if (record == null) {
            record = ShoppingRecord.builder().uid(uid).build();
        }

        log.info("uid = {}", uid);

        if (record.getId() == null) {
            // 新增
            List<RawItemDto> rawItemDtos = dto.getItems();
            List<ItemDto> itemDtos = new ArrayList<>();

            rawItemDtos.parallelStream().forEach(rawItemDto -> {
                String table = rawItemDto.getTable();

                if ("beverage".equals(table)) {
                    BeverageItemDto itemDto = new BeverageItemDto();
                    BeanUtils.copyProperties(rawItemDto, itemDto);
                    itemDtos.add(itemDto);
                } else if ("food".equals(table)) {
                    FoodItemDto itemDto = new FoodItemDto();
                    BeanUtils.copyProperties(rawItemDto, itemDto);
                    itemDtos.add(itemDto);
                } else {
                    SelectionItemDto itemDto = new SelectionItemDto();
                    BeanUtils.copyProperties(rawItemDto, itemDto);
                    itemDtos.add(itemDto);
                }
            });

            log.info("add items: {}", itemDtos);

            record.setItems(itemDtos);
            shoppingRecordMapper.insertSelective(record);
        } else {
            // 更新
            List<RawItemDto> rawItemDtos = dto.getItems();
            List<ItemDto> newItemDtos = new ArrayList<>();

            record.getItems().parallelStream().forEach(item -> {
                rawItemDtos.parallelStream().forEach(rawItemDto -> {
                    if (rawItemDto.getTable().equals(item.getTable()) && rawItemDto.getId().equals(item.getId())) {
                        // 原已存在记录
                        newItemDtos.add(rawItemDto);
                    } else {
                        newItemDtos.add(item);
                    }
                });
            });
            log.info("update items: {}", newItemDtos);

            record.setItems(newItemDtos);
            shoppingRecordMapper.updateByPrimaryKeySelective(record);
        }

        log.info("record: {}", record);

        return Result.success("更改成功", record);
    }

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
}
