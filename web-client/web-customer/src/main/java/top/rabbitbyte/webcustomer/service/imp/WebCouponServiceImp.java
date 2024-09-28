package top.rabbitbyte.webcustomer.service.imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.coupon.client.CouponFeignClient;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;
import top.rabbitbyte.webcustomer.service.WebCouponService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  19:16
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class WebCouponServiceImp implements WebCouponService {
    @Autowired
    private CouponFeignClient couponFeignClient;
    @Override
    public PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId) {
        return couponFeignClient.findNoReceivePage(customerId,pageParam.getCurrent(),pageParam.getSize()).getData() ;
    }

    @Override
    public Boolean receive(Long customerId, Long couponId) {
        return couponFeignClient.receive(customerId,couponId).isSuccess();
    }
}
