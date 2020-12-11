package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.User;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    Long userCount();
}