package cn.kanouakira;

import cn.kanouakira.entity.User;
import cn.kanouakira.service.TokenService;
import cn.kanouakira.service.UserService;
import cn.kanouakira.service.impl.TokenServiceImpl;
import cn.kanouakira.service.impl.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TokenTest {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @Test
    public void testToken(){
        User user = userService.selectUserById((long) 1);
        String token = tokenService.getToken(user);
        System.out.println("token:"+token);
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenServiceImpl.SECRET_KEY)).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        System.out.println("过期时间:"+jwt.getExpiresAt().toString());
        System.out.println("发送方:"+jwt.getClaim("iss").asString());
        System.out.println("接收方:"+jwt.getClaim("aud").asString());
    }
}
