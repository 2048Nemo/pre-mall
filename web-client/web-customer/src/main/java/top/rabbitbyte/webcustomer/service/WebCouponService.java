package top.rabbitbyte.webcustomer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;

public interface WebCouponService {
    PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId);

    Boolean receive(Long customerId, Long couponId);
}
