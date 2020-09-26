package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Merchandise;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface MerchandiseMapper extends tk.mybatis.mapper.common.Mapper<Merchandise> {
    int updateBatch(List<Merchandise> list);

    int updateBatchSelective(List<Merchandise> list);

    int batchInsert(@Param("list") List<Merchandise> list);

    int insertOrUpdate(Merchandise record);

    int insertOrUpdateSelective(Merchandise record);
}