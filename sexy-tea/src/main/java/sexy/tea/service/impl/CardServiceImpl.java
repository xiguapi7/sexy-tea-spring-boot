package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.CardMapper;
import sexy.tea.model.Card;
import sexy.tea.service.CardService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;

    @Autowired
    public CardServiceImpl(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    @Override
    public int updateBatch(List<Card> list) {
        return cardMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Card> list) {
        return cardMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Card> list) {
        return cardMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Card record) {
        return cardMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Card record) {
        return cardMapper.insertOrUpdateSelective(record);
    }

}
