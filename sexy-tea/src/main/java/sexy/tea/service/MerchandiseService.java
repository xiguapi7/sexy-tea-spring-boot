package sexy.tea.service;

import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Result;

/**
 * 商品服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface MerchandiseService extends UploadService {

    Result find(int pageNum, int pageSize);

    Result findByPrimaryKey(Integer primaryKey);

    Result saveOrUpdate(Merchandise merchandise);

    Result delete(Integer id);

    Result findByName(String name, int pageNum, int pageSize);
}
