package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Store;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface StoreMapper extends tk.mybatis.mapper.common.Mapper<Store> {
    int updateBatch(List<Store> list);

    int updateBatchSelective(List<Store> list);

    int batchInsert(@Param("list") List<Store> list);

    int insertOrUpdate(Store record);

    int insertOrUpdateSelective(Store record);

    List<Store> findByCity(@Param("city") String city);

    Long storeCount();
}