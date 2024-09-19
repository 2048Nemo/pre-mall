package top.rabbitbyte.serviceutil.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: config
 * @Author: nemo2048
 * @CreateTime: 2024-09-17  19:35
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "app.constants")
public class Constant {
    public static final String PAGE = "page";
    public static final String LIMIT = "limit";
    public static final String ORDER_FIELD = "orderField";
    public static final String ORDER = "order";
    public static final String ASC = "asc";
}
