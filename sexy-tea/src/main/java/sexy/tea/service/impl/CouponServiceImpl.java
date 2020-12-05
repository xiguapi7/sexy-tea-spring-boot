package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.CouponMapper;
import sexy.tea.service.CouponService;

/**
 * 优惠券服务接口实现类
 * <p>
 * TODO 优惠券功能实现
 * <p>
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
@Service
public class CouponServiceImpl implements CouponService {

    private final CouponMapper couponMapper;

    @Autowired
    public CouponServiceImpl(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }
}
