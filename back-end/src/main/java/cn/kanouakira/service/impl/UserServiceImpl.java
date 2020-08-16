package cn.kanouakira.service.impl;

import cn.kanouakira.entity.User;
import cn.kanouakira.mapper.UserMapper;
import cn.kanouakira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectUserPage() {
        return userMapper.selectUserPage();
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }
}
