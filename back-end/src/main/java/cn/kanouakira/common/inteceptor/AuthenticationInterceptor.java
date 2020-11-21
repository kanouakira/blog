package cn.kanouakira.common.inteceptor;

import cn.kanouakira.common.annotation.PassToken;
import cn.kanouakira.common.annotation.UserLoginToken;
import cn.kanouakira.entity.User;
import cn.kanouakira.service.TokenService;
import cn.kanouakira.service.UserService;
import cn.kanouakira.service.impl.TokenServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 自定义拦截器，获取token并验证
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Long userId = null;
        if (token != null){
            List<String> audience = JWT.decode(token).getAudience();
            // 根据token获取userId存入request域
            userId = Long.valueOf(JWT.decode(token).getAudience().get(0));
            request.setAttribute("userId", userId);
        }
        // 不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 有PassToken注解直接跳过验证
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }

        // 需要检查用户是否登录
        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                // 需要验证token
                Assert.notNull(token, "token must be not null");
                User user = userService.selectUserById(userId);
                Assert.notNull(user, "user not exist");
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenServiceImpl.SECRET_KEY)).build();
                DecodedJWT jwt = jwtVerifier.verify(token);
                System.out.println(jwt.getExpiresAt().toString());
                return true;
            }
        }
        return true;
    }
}
