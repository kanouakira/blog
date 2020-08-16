package cn.kanouakira.service;

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
