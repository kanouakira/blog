package cn.kanouakira;

import cn.kanouakira.entity.User;
import cn.kanouakira.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {

    @Autowired
    private UserService userService;

    /**
     * 测试用户分页查询
     */
    @Test
    public void testSelectUserPage(){
//        PageHelper.startPage(1, 1);
//        List<User> users = userService.selectUserPage();
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//        System.out.println(pageInfo);
    }

    /**
     * 测试根据id查询用户
     */
    @Test
    public void testSelectUserById(){
        System.out.println(userService.selectUserById((long) 1));
    }
}
