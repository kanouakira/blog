package cn.kanouakira.service.impl;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.utils.PageUtils;
import cn.kanouakira.entity.User;
import cn.kanouakira.mapper.UserMapper;
import cn.kanouakira.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageResult selectUserPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     */
    public PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> sysMenus = userMapper.selectUserPage();
        return new PageInfo<User>(sysMenus);
    }

    @Override
    public User selectUserById(long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public boolean isUserNameExist(String username) {
        return userMapper.isUserNameExist(username);
    }

    @Override
    public boolean saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public boolean deleteUserById(long id) {
        return userMapper.deleteUserById(id);
    }
}
