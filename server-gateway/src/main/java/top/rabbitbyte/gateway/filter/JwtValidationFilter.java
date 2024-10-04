package top.rabbitbyte.gateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.gateway.filter
 * @Author: nemo2048
 * @CreateTime: 2024-10-04  20:10
 * @Description: TODO
 * @Version: 1.0
 */

@Component
public class JwtValidationFilter implements GlobalFilter, Ordered {

    private static final String SECRET_KEY = "7bX2zQ3v5hK9cJ6r4e1i8o7u4a5s9d6f2g8h1j4k7l6p3o9q5w8e2r1t4y6o9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o9q5w8e2r1t4y6o9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o9q5w8e2r1t4y6o9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l4p3o6q9w8e1r4t6y9u7a5s1d6f3g8h2j5k7l"; // 替换为实际的密钥
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求头中获取 JWT
        String authHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            // 如果没有 JWT 或者 JWT 格式不正确，直接返回未授权
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(BEARER_PREFIX.length());

        // 验证 JWT
        //生成 HMAC 密钥，根据提供的字节数组长度选择适当的 HMAC 算法，并返回相应的 SecretKey 对象。
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        try {
            // 得到DefaultJwtParser
            JwtParser jwtParser = Jwts.parser()
                    // 设置签名的秘钥
                    .verifyWith(key)
                    .build();
            Jws<Claims> jws = jwtParser.parseSignedClaims(token);
            Claims claims = jws.getPayload();
            System.out.println(claims);
            // 如果验证成功，继续处理请求
            return chain.filter(exchange);
        } catch (Exception e) {
            // 如果验证失败，返回未授权
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0; // 可以根据需要调整优先级
    }
}