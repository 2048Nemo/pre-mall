package top.rabbitbyte.webcustomer.service;

import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.UserInfo;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;

public interface WebCustomerService {

    CustomerLoginVo getCustomerLoginInfo(Long customerId);

    CustomerInfoVo getCustomerInfo(Long customerId);

    UserInfo login(String code);

    Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm);

    String getCustomerOpenId(Long customerId);
}
