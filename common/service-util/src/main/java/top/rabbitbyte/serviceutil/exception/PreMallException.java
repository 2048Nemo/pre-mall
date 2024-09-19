package top.rabbitbyte.serviceutil.exception;

import lombok.Data;
import top.rabbitbyte.comon.utils.result.ResultCodeEnum;


/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.serviceutil.exception
 * @Author: nemo2048
 * @CreateTime: 2024-08-31  15:46
 * @Description: TODO
 * @Version: 1.0
 */

@Data
public class PreMallException extends RuntimeException {
    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public PreMallException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public PreMallException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "PreMallException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
