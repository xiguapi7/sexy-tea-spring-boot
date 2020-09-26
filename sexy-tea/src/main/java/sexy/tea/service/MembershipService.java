package sexy.tea.service;

import sexy.tea.model.Membership;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface MembershipService {


    int updateBatch(List<Membership> list);

    int updateBatchSelective(List<Membership> list);

    int batchInsert(List<Membership> list);

    int insertOrUpdate(Membership record);

    int insertOrUpdateSelective(Membership record);

}
