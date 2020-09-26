package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.MembershipMapper;
import sexy.tea.model.Membership;
import sexy.tea.service.MembershipService;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipMapper membershipMapper;

    @Autowired
    public MembershipServiceImpl(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

    @Override
    public int updateBatch(List<Membership> list) {
        return membershipMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Membership> list) {
        return membershipMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Membership> list) {
        return membershipMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Membership record) {
        return membershipMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Membership record) {
        return membershipMapper.insertOrUpdateSelective(record);
    }

}
