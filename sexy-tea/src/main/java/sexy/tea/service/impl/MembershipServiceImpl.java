package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.MembershipMapper;
import sexy.tea.service.MembershipService;

/**
 * 会员服务接口实现类
 * <p>
 * TODO 会员功能未实现
 * <p>
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

}
