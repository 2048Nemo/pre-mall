package top.rabbitbyte.coupon.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.comon.utils.result.ResultCodeEnum;
import top.rabbitbyte.coupon.mapper.CouponInfoMapper;
import top.rabbitbyte.coupon.mapper.CustomerCouponMapper;
import top.rabbitbyte.coupon.service.CouponInfoService;
import top.rabbitbyte.customer.client.CustomerInfoFeignClient;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.entity.coupon.CustomerCoupon;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.serviceutil.constant.RedisConstant;
import top.rabbitbyte.serviceutil.exception.PreMallException;


import java.util.Date;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.coupon.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-08-23  14:27
 * @Description: TODO
 * @Version: 1.0
 */

@Service
public class CouponInfoServiceImp extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {
    @Autowired
    private CouponInfoMapper couponInfoMapper;

    @Autowired
    private  CustomerCouponMapper customerCouponMapper;

    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;

    @Autowired
    private RedissonClient redissonClient;

    private static final Logger logger = LoggerFactory.getLogger(CouponInfoServiceImp.class);


    @Override
    public PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId) {
        IPage<NoReceiveCouponVo> page =  couponInfoMapper.findNoReceivePage(pageParam,customerId);
        return new PageVo(page.getRecords(), page.getPages(), page.getTotal());
    }
    //　TODO 优化领取优惠券方法，添加缓存
    @Override
    @Transactional
    public void receive(Long customerId, Long couponId) throws RuntimeException{
        //获取优惠券信息，首先先获取customer的信息
        //仅用于检查优惠券是否过期，不存在更新数据库信息，故不用加锁
        Result<CustomerInfoVo> customerInfoVo = customerInfoFeignClient.getCustomerInfo(customerId);
        CustomerInfoVo customerInfo = customerInfoVo.getData();

        CouponInfo couponInfo = couponInfoMapper.selectById(couponId);

        //1.拦截customer 的积分和会员等级限制
        if (customerInfo == null || couponInfo == null){
            throw  new PreMallException(ResultCodeEnum.DATA_ERROR);
        }
        if (couponInfo.getNeedLevel() > customerInfo.getLevel() || couponInfo.getNeedPoints() > customerInfo.getPoints()){
            throw new PreMallException(ResultCodeEnum.COUPON_USER_LIMIT_CONDITIONS);
        }

        //2.检查剩余优惠券生存周期，is_delete,status ?
        if (couponInfo.getIsDeleted() == 1 || couponInfo.getStatus() != 1){
            throw new PreMallException(ResultCodeEnum.COUPON_EXPIRE);
        }
        //3.检查优惠券数量库存检查
        if (couponInfo.getReceiveCount() >= couponInfo.getPublishCount()){
            throw new PreMallException(ResultCodeEnum.COUPON_LESS);
        }

        //4.数据写回数据库
        RLock lock = null;
        try {
            if (couponInfo.getPerLimit() > 0) {
                //关系到用户持有该优惠券数量检查必须与更新优惠券数量插入保证原子性，不过也可以在这边加一个double check减少分布式锁的竞争
                //查询Coupon customer  记录看有没有超出优惠券数量限制,数量是否足够避免超量，参数publish_count ,per_limit,
                LambdaQueryWrapper<CustomerCoupon> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(CustomerCoupon::getCouponId,couponId).eq(CustomerCoupon::getCustomerId,customerId);
                Long countCoupon = customerCouponMapper.selectCount(wrapper);
                if (countCoupon >= couponInfo.getPerLimit() || countCoupon >=  couponInfo.getPublishCount() ) {
                    throw new PreMallException(ResultCodeEnum.COUPON_USER_LIMIT);
                }
            }
            //获取分布式锁
            lock  = redissonClient.getLock(RedisConstant.COUPON_LOCK + customerId);
//            boolean flag = lock.tryLock(RedisConstant.COUPON_LOCK_WAIT_TIME,
//                    RedisConstant.COUPON_LOCK_LEASE_TIME, TimeUnit.SECONDS);
//            boolean flag = lock.tryLock();
            boolean flag =true;
            lock.lock();
            if (flag){
                //double check
                couponInfo = couponInfoMapper.selectById(couponId);
                if (couponInfo.getPerLimit() > 0) {
                    //关系到用户持有该优惠券数量检查必须与更新优惠券数量插入保证原子性，不过也可以在这边加一个double check减少分布式锁的竞争
                    //查询Coupon customer  记录看有没有超出优惠券数量限制,数量是否足够避免超量，参数publish_count ,per_limit,
                    LambdaQueryWrapper<CustomerCoupon> wrapper = new LambdaQueryWrapper<>();
                    wrapper.eq(CustomerCoupon::getCouponId,couponId).eq(CustomerCoupon::getCustomerId,customerId);
                    Long countCoupon = customerCouponMapper.selectCount(wrapper);
                    if (countCoupon >= couponInfo.getPerLimit() || countCoupon >=  couponInfo.getPublishCount() ) {
                        throw new PreMallException(ResultCodeEnum.COUPON_USER_LIMIT);
                    }
                }

                //由于分布式锁是加在用户id的，故可能会遇到优惠券这边的并发更新优惠券问题，但这边通过乐观锁实现了更新，需要判断一下乐观锁结果
                int row =  couponInfoMapper.updateReceiveCount(couponInfo.getId());
                if (row==0) {
                    throw new PreMallException(ResultCodeEnum.COUPON_USER_LIMIT);
                }
                //更新用户优惠券表记录
                this.saveCustomerCoupon(customerId,couponId,couponInfo.getExpireTime());
            }
        } catch (PreMallException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(lock != null){
                lock.unlock();
            }
        }
    }
    private  void saveCustomerCoupon(Long customerId,Long couponId,Date expireTime) throws PreMallException{
        //用户优惠券表记录
        CustomerCoupon customerCoupon = new CustomerCoupon();
        customerCoupon.setCouponId(couponId);
        customerCoupon.setCustomerId(customerId);
        customerCoupon.setStatus(1);
        customerCoupon.setReceiveTime(new Date(System.currentTimeMillis()));
        //TODO 可以试着优化过期时间使用策略设计模式设置多种过期策略
        customerCoupon.setExpireTime(expireTime);
        int rows =  customerCouponMapper.insert(customerCoupon);
        if (rows == 0){
            throw new PreMallException(ResultCodeEnum.UPDATE_ERROR);
        }
    }
}