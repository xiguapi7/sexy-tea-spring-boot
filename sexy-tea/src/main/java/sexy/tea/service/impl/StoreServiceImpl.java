package sexy.tea.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sexy.tea.mapper.StoreMapper;
import sexy.tea.model.Store;
import sexy.tea.model.common.Pager;
import sexy.tea.model.common.Result;
import sexy.tea.service.StoreService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public int updateBatch(List<Store> list) {
        return storeMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Store> list) {
        return storeMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Store> list) {
        return storeMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Store record) {
        return storeMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Store record) {
        return storeMapper.insertOrUpdateSelective(record);
    }

    @Override
    public Result find(int pageNum, int pageSize) {
        Page<Store> page = PageHelper.startPage(pageNum, pageSize);
        Example example = Example.builder(Store.class).build();
        example.createCriteria().andEqualTo("status", 1);
        List<Store> storeList = storeMapper.selectByExample(example);
        return Result.success("列表查询", Pager.<Store>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(storeList)
                .build());
    }

    @Override
    public Result findByCityName(int pageNum, int pageSize, String city) {
        if (StringUtils.isEmpty(city)) {
            return Result.business("参数错误, city: " + city, Optional.empty());
        }
        city = "%" + city + "%";
        Page<Store> page = PageHelper.startPage(pageNum, pageSize);
        storeMapper.findByCity(city);
        return Result.success("关键词:" + city, Pager.<Store>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .total(page.getTotal())
                .result(page.getResult())
                .build());
    }
}
