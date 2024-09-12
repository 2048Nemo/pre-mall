package top.rabbitbyte.model.form.customer.WeixinLoginFrom;

import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.form.customer
 * @Author: nemo2048
 * @CreateTime: 2024-09-10  00:26
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class WeixinLoginForm {
    private String code;
    private Detail detail;
}


