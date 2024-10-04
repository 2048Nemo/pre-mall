package top.rabbitbyte.comon.utils.token;

import top.rabbitbyte.comon.utils.result.ResultCodeEnum;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.token
 * @Author: nemo2048
 * @CreateTime: 2024-10-03  22:21
 * @Description: TODO
 * @Version: 1.0
 */
public class JWTException extends RuntimeException {
    public static final ResultCodeEnum TOKEN_IS_EMPTY = ResultCodeEnum.TOKEN_IS_EMPTY;
    public static final ResultCodeEnum TOKEN_IS_EXPIRED = ResultCodeEnum.TOKEN_IS_EXPIRED;
    public static final ResultCodeEnum TOKEN_IS_WRONG = ResultCodeEnum.TOKEN_IS_WRONG;

    private int errno;
    private String errmsg;


    public JWTException(ResultCodeEnum errno, String errmsg) {
        this.errno = errno.getCode();
        //默认覆盖错误信息
        this.errmsg = errmsg;
    }
}
