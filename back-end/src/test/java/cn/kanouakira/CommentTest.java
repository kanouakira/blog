package cn.kanouakira;

import cn.kanouakira.entity.Comment;
import cn.kanouakira.service.CommentService;
import cn.kanouakira.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentTest {

    @Autowired
    private CommentService commentService;

    /**
     * 测试根据id获取评论信息
     */
    @Test
    public void testSelectCommentById(){
        Comment comment = commentService.selectCommentById((long) 3);
        System.out.println(comment);
        System.out.println("该评论的用户信息"+comment.getUser());
        System.out.println("该评论回复的评论信息"+comment.getReplyComment());
        System.out.println("该评论的子评论集"+comment.getChildComments());
    }
}
