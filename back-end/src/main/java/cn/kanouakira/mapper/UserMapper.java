package cn.kanouakira.mapper;

import cn.kanouakira.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    User selectUserById(long id);

    /**
     * 查询username是否存在
     * @param username
     * @return
     */
    boolean isUserNameExist(String username);

    /**
     * 根据username返回一个用户
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 新增用户
     * @param user
     * @return
     */
    boolean saveUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    boolean deleteUserById(long id);
}
