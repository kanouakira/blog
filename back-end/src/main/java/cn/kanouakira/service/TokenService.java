package cn.kanouakira.service;

import cn.kanouakira.entity.User;

public interface TokenService {
    /**
     * 生成token
     */
    public String getToken(User user);
}
