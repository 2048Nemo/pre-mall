package top.rabbitbyte.comon.utils.token.injection;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.rabbitbyte.comon.utils.result.JWTUser;
import top.rabbitbyte.comon.utils.token.JWTException;
import top.rabbitbyte.comon.utils.token.TokenSolver;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.token.injection
 * @Author: nemo2048
 * @CreateTime: 2024-10-03  21:46
 * @Description: TODO
 * @Version: 1.0
 */

@Slf4j
@Component
public class JWTResolver implements HandlerMethodArgumentResolver {

    //缓存
    private static Cache<String, JWTUser> cache =
            CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(3, TimeUnit.MINUTES).build();

    @Autowired
    private TokenSolver tokenSolver;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JWT.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String token = webRequest.getHeader("Authorization");
        JWTUser user = null;
        if (token == null && parameter.getParameterAnnotation(JWT.class).required()) {
            log.info("用户的Authorization头为空,无法获取jwt");
            throw new JWTException(JWTException.TOKEN_IS_EMPTY, "用户的Authorization头为空,无法获取jwt");
        } else if (token != null && (user = cache.getIfPresent(token)) == null) {   //试图从缓存获取

            try {
                user = tokenSolver.solve(token);
                cache.put(token, user);
            } catch (TokenExpiredException e) {
                log.info("jwt已过期，过期时间：{}", e.getMessage());
                if (parameter.getParameterAnnotation(JWT.class).checkExpired()) {
                    throw new JWTException(JWTException.TOKEN_IS_EXPIRED, "凭证已过期");
                }
            } catch (Exception e) {
                log.info("jwt解析失败");
                if (parameter.getParameterAnnotation(JWT.class).required()) {
                    throw new JWTException(JWTException.TOKEN_IS_WRONG, "用户的Authorization头错误,无法获取jwt");
                }
            }
        }

        log.info("jwt解析结果为：{}", user);

        return user;
    }

}
