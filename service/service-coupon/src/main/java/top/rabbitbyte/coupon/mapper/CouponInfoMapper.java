package top.rabbitbyte.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;
import top.rabbitbyte.model.vo.coupon.NoUseCouponVo;

@Mapper
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {
    IPage<NoUseCouponVo> findNoUsePage(Page<CouponInfo> pageParam, @Param("customerId") Long customerId);
    IPage<NoReceiveCouponVo> findNoReceivePage(Page<CouponInfo> pageParam, @Param("customerId") Long customerId);

}
