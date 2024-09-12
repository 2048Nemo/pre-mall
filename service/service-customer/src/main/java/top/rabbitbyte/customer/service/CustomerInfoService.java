package top.rabbitbyte.customer.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.rabbitbyte.model.entity.customer.CustomerInfo;
import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.UserInfo;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;


public interface CustomerInfoService extends IService<CustomerInfo> {
    //微信小程序登录接口
    UserInfo login(String code);

    //获取客户登录信息
    CustomerLoginVo getCustomerLoginInfo(Long customerId);
    //获取客户信息
    CustomerInfoVo getCustomerInfo(Long customerId);

    //更新客户微信手机号码
    Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm);

    String getCustomerOpenId(Long customerId);
}
