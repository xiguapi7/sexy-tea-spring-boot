package sexy.tea.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sexy.tea.mapper.CouponMapper;
import sexy.tea.model.Coupon;
import sexy.tea.service.CouponService;

import java.util.List;

/**
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

    @Override
    public int updateBatch(List<Coupon> list) {
        return couponMapper.updateBatch(list);
    }

    @Override
    public int updateBatchSelective(List<Coupon> list) {
        return couponMapper.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<Coupon> list) {
        return couponMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(Coupon record) {
        return couponMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(Coupon record) {
        return couponMapper.insertOrUpdateSelective(record);
    }

}
