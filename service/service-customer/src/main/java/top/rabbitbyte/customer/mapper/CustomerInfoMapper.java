package top.rabbitbyte.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.rabbitbyte.model.entity.customer.CustomerInfo;


@Mapper
@Repository
public interface CustomerInfoMapper extends BaseMapper<CustomerInfo> {
}
