package cn.kanouakira.controller;

import cn.kanouakira.common.lang.Result;
import cn.kanouakira.common.utils.HashUtil;
import cn.kanouakira.entity.User;
import cn.kanouakira.service.TokenService;
import cn.kanouakira.service.UserService;
import com.auth0.jwt.JWT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取token的表现层
 */
@RestController
@Api(description = "验证操作", tags = {"验证操作接口"})
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    // todo 未验证改接口是否正常
    @ApiOperation("登录验证获取token")
    @PostMapping()
    public Result login(@RequestBody User user){
        User queryUser = userService.selectUserByUsername(user.getUsername());
        if (!userService.isUserNameExist(user.getUsername())){
            return Result.fail("用户名或密码错误！");
        }
        if (!HashUtil.hash(user.getPassword(), "SHA-256").equals(queryUser.getPassword())){
            return Result.fail("用户名或密码错误！");
        }
        String token = tokenService.getToken(queryUser);

        queryUser.setLastLogin(LocalDateTime.now());
        userService.updateUser(queryUser);
        Map<String, Object> data = new HashMap<>();
        data.put("user", queryUser);
        data.put("token", token);
        return Result.succ(data);
    }

    @GetMapping()
    public Result getUser(@RequestParam String token){
        HashMap<String, Object> data = new HashMap<>();
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userService.selectUserById(Long.valueOf(userId));
        if (user.getStatus() != 5){
            return Result.fail("权限不足");
        }
        data.put("role",user.getRoleName());
        data.put("name",user.getName()!=null ? user.getName() : user.getUsername());
        data.put("avatar",user.getAvatar());
        data.put("status",user.getStatus());
        return Result.succ(data);
    }

    @PostMapping("/logout")
    public Result logout(){
        return Result.succ(null);
    }

}
