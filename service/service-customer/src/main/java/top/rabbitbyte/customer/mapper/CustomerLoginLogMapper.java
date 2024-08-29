package top.rabbitbyte.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.rabbitbyte.model.entity.customer.CustomerLoginLog;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.customer.mapper
 * @Author: nemo2048
 * @CreateTime: 2024-08-27  20:28
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface CustomerLoginLogMapper extends BaseMapper<CustomerLoginLog> {
}
