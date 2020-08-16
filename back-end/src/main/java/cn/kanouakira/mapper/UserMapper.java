package cn.kanouakira.mapper;

import cn.kanouakira.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-09
 */
@Mapper
public interface UserMapper {
    /**
     * 自定义sql分页
     * @return
     */
    List<User> selectUserPage();

    /**
     * 根据id返回一个用户
     * @param id
     * @return
     */
    User selectUserById(Long id);
}
