package sexy.tea.service;

import sexy.tea.model.ShoppingRecord;
import sexy.tea.model.common.Result;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface ShoppingRecordService {

    Result find(int pageNum, int pageSize);

    Result findByUid(Long uid);

    Result saveOrUpdate(ShoppingRecord record);

    Result delete(Long uid);

    void updateShoppingRecordById(Long id);
}
