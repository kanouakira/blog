package cn.kanouakira.service;

import cn.kanouakira.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-23
 */
public interface RoleService {
    List<Role> findAllRoles();
}
