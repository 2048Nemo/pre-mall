package top.rabbitbyte.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.rabbitbyte.model.entity.coupon.CustomerCoupon;

@Mapper
public interface CustomerCouponMapper extends BaseMapper<CustomerCoupon> {
}
