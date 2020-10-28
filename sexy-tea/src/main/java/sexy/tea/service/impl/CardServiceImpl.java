package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sexy.tea.exception.BusinessException;
import sexy.tea.mapper.CardMapper;
import sexy.tea.model.Card;
import sexy.tea.model.common.Result;
import sexy.tea.service.CardService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Optional;

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

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result saveOrUpdate(Card card) {
        if (card == null || card.getUid() == null || card.getUid() <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        cardMapper.insertOrUpdateSelective(card);
        return Result.success("更改成功", card);
    }

    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public Result deleteById(Long id) {
        if (id == null || id <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        int row = cardMapper.deleteByPrimaryKey(id);
        return row <= 0 ? Result.business("注销星礼卡失败", Optional.empty()) : Result.success("注销星礼卡成功", Optional.empty());
    }

    @Override
    public Result findByUid(Long uid) {
        if (uid == null || uid <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        Example example = Example.builder(Card.class).build();
        example.createCriteria().andEqualTo("status", 1).andEqualTo("uid", uid);
        return Result.success("uid: " + uid, cardMapper.selectByExample(example));
    }

    @Override
    public Result findByCardName(String cardName) {
        if (StringUtils.isEmpty(cardName)) {
            return Result.business("参数错误", Optional.empty());
        }
        Example example = Example.builder(Card.class).build();
        example.createCriteria().andEqualTo("status", 1).andEqualTo("card_name", cardName);
        return Result.success("关键词: " + cardName, cardMapper.selectByExample(example));
    }

    @Override
    public Result findByCardId(Integer cardId) {
        if (cardId == null || cardId <= 0) {
            return Result.business("参数错误", Optional.empty());
        }
        return Result.success("cardId: " + cardId, cardMapper.selectByPrimaryKey(cardId));
    }
}
