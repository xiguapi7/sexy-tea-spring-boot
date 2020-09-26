package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.SelectionMapper;
import sexy.tea.model.Selection;
import sexy.tea.service.SelectionService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class SelectionServiceImpl implements SelectionService {

    private final SelectionMapper selectionMapper;

    @Autowired
    public SelectionServiceImpl(SelectionMapper selectionMapper) {
        this.selectionMapper = selectionMapper;
    }

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

}
