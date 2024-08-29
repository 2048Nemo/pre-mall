package top.rabbitbyte.model.form.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.form.customer
 * @Author: nemo2048
 * @CreateTime: 2024-08-27  18:54
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class UpdateWxPhoneForm {
    @Schema(description = "客户Id")
    private Long customerId;

    private String code;

}
