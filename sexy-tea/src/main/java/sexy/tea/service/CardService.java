package sexy.tea.service;

import sexy.tea.model.Card;
import sexy.tea.model.common.Result;

/**
 * 星礼卡服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface CardService {

    Result saveOrUpdate(Card card);

    Result deleteById(Long id);

    Result findByUid(Long uid);

    Result findByCardName(String cardName);

    Result findByCardId(Integer id);
}
