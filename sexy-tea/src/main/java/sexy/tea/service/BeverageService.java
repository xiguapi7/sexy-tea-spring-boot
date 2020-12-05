package sexy.tea.service;

import sexy.tea.model.Beverage;
import sexy.tea.model.common.Result;

/**
 * 饮品服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface BeverageService {

    Result find(int pageNum, int pageSize);

    Result findById(Long primaryKey);

    Result saveOrUpdate(Beverage beverage);

    Result delete(Long id);

    Result findByName(String name, int pageNum, int pageSize);

    Result itemsMenu(int type, int pageNum, int pageSize);
}
