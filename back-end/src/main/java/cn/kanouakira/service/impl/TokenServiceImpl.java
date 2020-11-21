package cn.kanouakira.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import cn.kanouakira.entity.User;
import cn.kanouakira.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service("TokenService")
public class TokenServiceImpl implements TokenService {
    // token加盐的密钥
    public static final String SECRET_KEY = "GUESS_MY_SECRET_KEY";
    // token有效期为10天
    public static final int CALENDAR_FIELD = Calendar.DATE;
    public static final int CALENDAR_INTERVAL = 10;

    @Override
    public String getToken(User user) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                // 添加密码到时候根据这个JWT.require(Algorithm.HMAC256(user.getPassword())).build()返回一个验证器验证是否token合法
                // 设置token有效期.withExpiresAt(date)
                .withExpiresAt(nowTime.getTime())
//                .withClaim("iss","Service")
//                .withClaim("aud","Browser")
                .sign(Algorithm.HMAC256(SECRET_KEY));
        return token;
    }
}
