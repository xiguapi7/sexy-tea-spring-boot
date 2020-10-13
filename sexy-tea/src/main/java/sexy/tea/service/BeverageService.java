package sexy.tea.service;

import sexy.tea.model.Beverage;
import sexy.tea.model.common.Result;
import sexy.tea.model.dto.MinioDto;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface BeverageService {

    int updateBatch(List<Beverage> list);

    int updateBatchSelective(List<Beverage> list);

    int batchInsert(List<Beverage> list);

    int insertOrUpdate(Beverage record);

    int insertOrUpdateSelective(Beverage record);

    Result find(int pageNum, int pageSize);

    Result findByPrimaryKey(Integer primaryKey);

    Result saveOrUpdate(Beverage beverage);

    Result uploadImage(MinioDto dto, String beverageId);

    Result delete(Integer id);
}
