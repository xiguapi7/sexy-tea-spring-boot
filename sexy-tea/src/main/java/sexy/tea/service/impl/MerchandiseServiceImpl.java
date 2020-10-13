package sexy.tea.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.MerchandiseMapper;
import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Result;
import sexy.tea.service.MerchandiseService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class MerchandiseServiceImpl implements MerchandiseService {

    private final MerchandiseMapper merchandiseMapper;

    @Autowired
    public MerchandiseServiceImpl(MerchandiseMapper merchandiseMapper) {
        this.merchandiseMapper = merchandiseMapper;
    }

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
}
