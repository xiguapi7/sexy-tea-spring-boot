package sexy.tea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sexy.tea.model.Membership;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Mapper
public interface MembershipMapper extends tk.mybatis.mapper.common.Mapper<Membership> {
    int updateBatch(List<Membership> list);

    int updateBatchSelective(List<Membership> list);

    int batchInsert(@Param("list") List<Membership> list);

    int insertOrUpdate(Membership record);

    int insertOrUpdateSelective(Membership record);
}