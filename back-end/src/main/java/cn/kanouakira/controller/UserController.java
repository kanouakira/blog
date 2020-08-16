package cn.kanouakira.controller;

import cn.kanouakira.common.lang.Result;
import cn.kanouakira.entity.User;
import cn.kanouakira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getUser(@PathVariable("id") Long id){
        User user = userService.selectUserById(id);
        user.setPassword(null);
        return Result.succ(200, "查询成功", user);
    }

}
