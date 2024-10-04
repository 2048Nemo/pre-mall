package top.rabbitbyte.comon.utils.token.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.token.injection
 * @Author: nemo2048
 * @CreateTime: 2024-10-03  21:46
 * @Description: TODO
 * @Version: 1.0
 */
public class SolverConfig implements WebMvcConfigurer {
    @Autowired
    private JWTResolver jwtResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtResolver);
    }
}
