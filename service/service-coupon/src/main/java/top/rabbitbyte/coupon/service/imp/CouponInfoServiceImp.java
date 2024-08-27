package top.rabbitbyte.coupon.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.coupon.mapper.CouponInfoMapper;
import top.rabbitbyte.coupon.service.CouponInfoService;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.CouponInfoVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;
import top.rabbitbyte.model.vo.coupon.NoUseCouponVo;

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
    @Override
    public PageVo<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, Long customerId) {
        IPage<NoReceiveCouponVo> page =  couponInfoMapper.findNoReceivePage(pageParam,customerId);
        return new PageVo(page.getRecords(), page.getPages(), page.getTotal());
    }
}
