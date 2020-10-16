package sexy.tea.service;

import sexy.tea.model.Card;
import sexy.tea.model.common.Result;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface CardService {

    int updateBatch(List<Card> list);

    int updateBatchSelective(List<Card> list);

    int batchInsert(List<Card> list);

    int insertOrUpdate(Card record);

    int insertOrUpdateSelective(Card record);

    Result createOrUpdate(Card card);

    Result deleteById(Integer id);

    Result selectByUid(Integer uid);

    Result selectByCardName(String cardName);

    Result selectByCardId(Integer id);
}
