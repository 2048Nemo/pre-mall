package top.rabbitbyte.webcustomer.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.customer.client.CustomerInfoFeignClient;
import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.UserInfo;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;
import top.rabbitbyte.webcustomer.service.WebCustomerService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  18:43
 * @Description:
 * @Version: 1.0
 */
@Service
public class WebCustomerServiceImp implements WebCustomerService {

    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;
    @Override
    public CustomerLoginVo getCustomerLoginInfo(Long customerId) {
        return customerInfoFeignClient.getCustomerLoginInfo(customerId).getData();
    }

    @Override
    public CustomerInfoVo getCustomerInfo(Long customerId) {
        return customerInfoFeignClient.getCustomerInfo(customerId).getData();
    }

    @Override
    public UserInfo login(String code) {
        return customerInfoFeignClient.login(code).getData();
    }

    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        return customerInfoFeignClient.updateWxPhoneNumber(updateWxPhoneForm).getData();
    }

    @Override
    public String getCustomerOpenId(Long customerId) {
        return customerInfoFeignClient.getCustomerOpenId(customerId).getData();
    }
}
