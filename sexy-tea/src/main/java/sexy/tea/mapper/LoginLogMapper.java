package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.LoginLog;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface LoginLogMapper extends tk.mybatis.mapper.common.Mapper<LoginLog> {
    int updateBatch(List<LoginLog> list);

    int updateBatchSelective(List<LoginLog> list);

    int batchInsert(@Param("list") List<LoginLog> list);

    int insertOrUpdate(LoginLog record);

    int insertOrUpdateSelective(LoginLog record);
}