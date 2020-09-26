package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.BeverageMapper;
import sexy.tea.model.Beverage;
import sexy.tea.service.BeverageService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class BeverageServiceImpl implements BeverageService {

    private final BeverageMapper beverageMapper;

    @Autowired
    public BeverageServiceImpl(BeverageMapper beverageMapper) {
        this.beverageMapper = beverageMapper;
    }

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

}
