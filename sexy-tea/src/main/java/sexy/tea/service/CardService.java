package sexy.tea.service;

import sexy.tea.model.Card;

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

}
