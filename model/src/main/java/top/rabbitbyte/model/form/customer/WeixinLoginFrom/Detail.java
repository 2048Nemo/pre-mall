package top.rabbitbyte.model.form.customer.WeixinLoginFrom;

import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.form.customer.WeixinLoginFrom
 * @Author: nemo2048
 * @CreateTime: 2024-09-10  00:46
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Detail {
    public String signature;
    public String rawData;
    public String encryptedData;
    public String iv;
}
