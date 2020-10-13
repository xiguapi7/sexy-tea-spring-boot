package sexy.tea.service;

import sexy.tea.model.City;
import sexy.tea.model.common.Result;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface CityService {


    int updateBatch(List<City> list);

    int updateBatchSelective(List<City> list);

    int batchInsert(List<City> list);

    int insertOrUpdate(City record);

    int insertOrUpdateSelective(City record);

    Result find(int pageNum, int pageSize);

    Result findByCityName(String cityName);
}
