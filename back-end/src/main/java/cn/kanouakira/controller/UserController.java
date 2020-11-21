package cn.kanouakira.controller;

import cn.kanouakira.common.annotation.UserLoginToken;
import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.lang.Result;
import cn.kanouakira.common.utils.HashUtil;
import cn.kanouakira.entity.User;
import cn.kanouakira.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-09
 */
@RestController
@Api(description = "用户操作", tags = {"用户操作接口"})
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户分页
     * @return
     */
    @ApiOperation("查询所有用户分页")
    @GetMapping
    public Result getUsers(@RequestParam("page") Integer current_page,
                           @RequestParam("per_page") Integer per_page){
        PageRequest pageRequest = new PageRequest(current_page, per_page);
        return Result.succ(userService.selectUserPage(pageRequest));
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @ApiOperation("根据id获取用户信息")
    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") long id){
        User user = userService.selectUserById(id);
        user.setPassword(null);
        return Result.succ(200, "查询成功", user);
    }

    /**
     * 新增一个用户
     * @param user
     * @return
     */
    @ApiOperation("新增一个用户")
    @PostMapping
    public Result createUser(@RequestBody User user){
        boolean result = userService.isUserNameExist(user.getUsername());
        if(result){
            return Result.fail("用户名已存在");
        }
        user.setPassword(HashUtil.hash(user.getPassword(),"SHA-256"));
        return Result.succ(userService.saveUser(user));
    }

    /**
     * 根据id修改用户
     * @param id
     * @param user
     * @return
     */
    @ApiOperation("根据id修改用户")
    @UserLoginToken
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable("id") Long id,@RequestBody User user){
        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer tokenStatus = userService.selectUserById((Long) request.getAttribute("userId")).getStatus();

        // 最高等级的管理员无视此条规则
        if (tokenStatus != 5){
            // 仅可修改自己的资料
            if (!request.getAttribute("userId").equals(id)){
                return Result.fail("操作失败");
            }
        }
        user.setId(id);
        if (!StringUtils.isEmpty(user.getPassword())){
            user.setPassword(HashUtil.hash(user.getPassword(),"SHA-256"));
        }

        return Result.succ(userService.updateUser(user) ? user : null);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation("根据id删除用户")
    @UserLoginToken
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable("id") long id){
        return Result.succ(userService.deleteUserById(id));
    }
}
