package sexy.tea.service;

import sexy.tea.common.Result;
import sexy.tea.model.Merchandise;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface MerchandiseService {


    int updateBatch(List<Merchandise> list);

    int updateBatchSelective(List<Merchandise> list);

    int batchInsert(List<Merchandise> list);

    int insertOrUpdate(Merchandise record);

    int insertOrUpdateSelective(Merchandise record);

    Result find(int pageNum, int pageSize);
}
