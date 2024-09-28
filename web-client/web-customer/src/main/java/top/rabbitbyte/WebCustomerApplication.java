package top.rabbitbyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer
 * @Author: nemo2048
 * @CreateTime: 2024-09-28  19:09
 * @Description:
 * @Version: 1.0
 */
@SpringBootApplication(scanBasePackages="top.rabbitbyte.webcustomer",exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class WebCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebCustomerApplication.class, args);
    }
}