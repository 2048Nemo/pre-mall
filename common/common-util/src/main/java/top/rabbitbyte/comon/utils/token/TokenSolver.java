package top.rabbitbyte.comon.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.rabbitbyte.comon.utils.result.JWTUser;

import java.lang.reflect.Field;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.token
 * @Author: nemo2048
 * @CreateTime: 2024-10-03  21:53
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class TokenSolver {

    @Value("${jwt.pub-key-file-name}")
    private String pubFile;

    public RSAKeyProvider keyProvider = new RSAKeyProvider() {

        RSAPublicKey key;

        @Override
        public RSAPublicKey getPublicKeyById(String s) {
            if (key == null) key = RSAKeysReader.readRsaPub(pubFile);
            return key;
        }

        @Override
        public RSAPrivateKey getPrivateKey() {
            return null;
        }

        @Override
        public String getPrivateKeyId() {
            return null;
        }
    };

    /**
     * JWT Token转JWTUser
     *
     * @param token
     * @return
     * @throws Exception
     */
    public JWTUser solve(String token) throws Exception {
        if (StringUtils.isEmpty(token)) return null;
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.RSA256(keyProvider);
        //会自动验证过期时间
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        JWTUser jwtUser = new JWTUser();

        //通过反射构造JWTUser对象
        for (Field field : jwtUser.getClass().getDeclaredFields()) {
            String value = decodedJWT.getClaim(field.getName()).asString();
            field.setAccessible(true);
            field.set(jwtUser, value);
        }

        return jwtUser;
    }
}