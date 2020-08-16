package cn.kanouakira.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 最后登录
     */
    private LocalDateTime lastLogin;

    /**
     * 角色名
     */
    private String roleName;

}
