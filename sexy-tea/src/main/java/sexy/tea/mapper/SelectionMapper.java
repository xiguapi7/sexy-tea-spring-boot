package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Selection;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface SelectionMapper extends tk.mybatis.mapper.common.Mapper<Selection> {
    int updateBatch(List<Selection> list);

    int updateBatchSelective(List<Selection> list);

    int batchInsert(@Param("list") List<Selection> list);

    int insertOrUpdate(Selection record);

    int insertOrUpdateSelective(Selection record);
}