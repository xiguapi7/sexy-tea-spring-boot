package sexy.tea.service;

import sexy.tea.model.Merchandise;
import sexy.tea.model.common.Result;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface MerchandiseService extends UploadService {


    int updateBatch(List<Merchandise> list);

    int updateBatchSelective(List<Merchandise> list);

    int batchInsert(List<Merchandise> list);

    int insertOrUpdate(Merchandise record);

    int insertOrUpdateSelective(Merchandise record);

    Result find(int pageNum, int pageSize);

    Result findByPrimaryKey(Integer primaryKey);

    Result saveOrUpdate(Merchandise merchandise);

    Result delete(Integer id);

    Result findByName(String name, int pageNum, int pageSize);
}
