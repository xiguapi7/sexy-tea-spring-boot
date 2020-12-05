package sexy.tea.service;

import sexy.tea.model.Selection;
import sexy.tea.model.common.Result;

/**
 * 精选服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface SelectionService {

    Result find(int pageNum, int pageSize);

    Result findById(Long primaryKey);

    Result saveOrUpdate(Selection selection);

    Result delete(Long id);

    Result findByName(String name, int pageNum, int pageSize);

    Result itemsMenu(int pageNum, int pageSize);
}
