package cn.kanouakira;

import cn.kanouakira.entity.Comment;
import cn.kanouakira.service.CommentService;
import cn.kanouakira.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TagTest {

    @Autowired
    private TagService tagService;

    /**
     * 测试标签分页查询
     */
    @Test
    public void testSelectTagPage(){
//        tagService.selectTagPage().forEach(tag -> System.out.println(tag));
    }
}
