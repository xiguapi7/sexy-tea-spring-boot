package sexy.tea.service;

import sexy.tea.model.common.Result;

/**
 * 城市服务接口
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface CityService {

    Result find(int pageNum, int pageSize);

    Result findByCityName(int pageNum, int pageSize, String cityName);

    Result search(int pageNum, int pageSize, String cityName);

    Result info();
}
