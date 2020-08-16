package cn.kanouakira;

import cn.kanouakira.entity.Post;
import cn.kanouakira.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PostTest {

    @Autowired
    private PostService postService;

    /**
     * 测试文章分页
     */
    @Test
    public void testSelectPostPage(){
        PageHelper.startPage(1, 2);
        List<Post> posts = postService.selectPostPage((long) 1,"Java", "manage", "");
        PageInfo<Post> pageInfo = new PageInfo<>(posts);
        System.out.println(pageInfo);
    }

    /**
     * 测试根据id获取文章内容
     */
    @Test
    public void testSelectPostById(){
        Post post = postService.selectPostById((long) 36);
//        Assert.notNull(post,"post must not null");
        // 打印文章所有的标签
        post.getTags().forEach(tag -> System.out.println(tag));
        // 打印文章所有的评论
//        post.getComments().forEach(comment -> System.out.println(comment));
    }
}
