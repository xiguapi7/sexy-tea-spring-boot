package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.SysLog;

import java.util.List;

/**
 *
 * author 大大大西西瓜皮🍉
 * date 18:00 2020-10-13
 * description: 
 */
@Mapper
public interface SysLogMapper extends tk.mybatis.mapper.common.Mapper<SysLog> {
    int updateBatch(List<SysLog> list);

    int updateBatchSelective(List<SysLog> list);

    int batchInsert(@Param("list") List<SysLog> list);

    int insertOrUpdate(SysLog record);

    int insertOrUpdateSelective(SysLog record);
}