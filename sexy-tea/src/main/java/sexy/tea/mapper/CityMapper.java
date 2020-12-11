package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.City;
import sexy.tea.model.vo.CityVO;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface CityMapper extends tk.mybatis.mapper.common.Mapper<City> {

    int updateBatch(List<City> list);

    int updateBatchSelective(List<City> list);

    int batchInsert(@Param("list") List<City> list);

    int insertOrUpdate(City record);

    int insertOrUpdateSelective(City record);

    List<City> search(@Param("cityName") String cityName);

    List<CityVO> cityVOList();
}