package cn.kanouakira.service.impl;

import cn.kanouakira.entity.Role;
import cn.kanouakira.mapper.RoleMapper;
import cn.kanouakira.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }
}
