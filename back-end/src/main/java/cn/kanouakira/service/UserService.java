package cn.kanouakira.service;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-09
 */
public interface UserService {
    /**
     * 自定义sql分页
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest
     * @return PageResult
     */
    PageResult selectUserPage(PageRequest pageRequest);

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
