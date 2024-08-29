package top.rabbitbyte.coupon.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.rabbitbyte.coupon.mapper.CouponInfoMapper;
import top.rabbitbyte.coupon.mapper.CustomerCouponMapper;
import top.rabbitbyte.coupon.service.CouponInfoService;
import top.rabbitbyte.customer.mapper.CustomerInfoMapper;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.entity.coupon.CustomerCoupon;
import top.rabbitbyte.model.entity.customer.CustomerInfo;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;


import java.util.Date;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.coupon.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-08-23  14:27
 * @Description: TODO
 * @Version: 1.0
 */
@Transactional
@Service
public class CouponInfoServiceImp extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private  CustomerCouponMapper customerCouponMapper;

    @Resource
    private  CustomerInfoMapper customerInfoMapper;

    @Override
    public PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId) {
        IPage<NoReceiveCouponVo> page =  couponInfoMapper.findNoReceivePage(pageParam,customerId);
        return new PageVo(page.getRecords(), page.getPages(), page.getTotal());
    }
    //　TODO 优化领取优惠券方法，添加缓存
    @Override
    public Boolean receive(Long customerId, Long couponId) {
        //获取优惠券信息，首先先获取customer的信息
        CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);
        CouponInfo couponInfo = couponInfoMapper.selectById(couponId);
        //1.拦截customer 的积分和会员等级限制
        if (customerInfo == null || couponInfo == null){
            return false;
        }
        if (couponInfo.getNeedLevel() > customerInfo.getLevel() || couponInfo.getNeedPoints() > customerInfo.getPoints()){
            return false;
        }

        //2.检查剩余优惠券生存周期，is_delete,status ?
        if (couponInfo.getIsDeleted() == 1 || couponInfo.getStatus() != 1){
            return false;
        }
        //查询Coupon customer  记录看有没有超出优惠券数量限制,数量是否足够避免超量，参数publish_count ,per_limit,
        LambdaQueryWrapper<CustomerCoupon> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CustomerCoupon::getCouponId,couponId).eq(CustomerCoupon::getCustomerId,customerId);
        Long countCoupon = customerCouponMapper.selectCount(wrapper);
        if (countCoupon >= couponInfo.getPerLimit() || countCoupon >=  couponInfo.getPublishCount() ) {
            return false ;
        }

        //3.数据写回数据库
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.setCouponId(couponId);
        customerCoupon.setCustomerId(customerId);
        customerCoupon.setStatus(1);
        customerCoupon.setReceiveTime(new Date(System.currentTimeMillis()));
        //TODO 可以试着优化过期时间使用策略设计模式设置多种过期策略
        customerCoupon.setExpireTime(couponInfo.getExpireTime());
        customerCouponMapper.insert(customerCoupon);
        return true;
    }
}
