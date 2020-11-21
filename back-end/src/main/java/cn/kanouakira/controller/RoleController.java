package cn.kanouakira.controller;

import cn.kanouakira.common.lang.Result;
import cn.kanouakira.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-23
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    public Result getRoles(){
        return Result.succ(roleService.findAllRoles());
    }
}
