package cn.kanouakira.mapper;

import cn.kanouakira.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> findAllRoles();
}
