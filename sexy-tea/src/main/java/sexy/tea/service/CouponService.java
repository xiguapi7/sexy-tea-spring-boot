package sexy.tea.service;

import sexy.tea.model.Coupon;

import java.util.List;

/**
 * author 大大大西西瓜皮🍉
 * date 15:10 2020-09-26
 * description:
 */
public interface CouponService {


    int updateBatch(List<Coupon> list);

    int updateBatchSelective(List<Coupon> list);

    int batchInsert(List<Coupon> list);

    int insertOrUpdate(Coupon record);

    int insertOrUpdateSelective(Coupon record);

}
