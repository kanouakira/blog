package cn.kanouakira;

import cn.kanouakira.mapper.CategoryMapper;
import cn.kanouakira.service.CategoryService;
import cn.kanouakira.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    /**
     * 测试标签分类内容的分页
     */
    @Test
    public void testSelectCategoryPage(){
        categoryService.selectCategoryPage().forEach(category -> System.out.println(category));
    }
}
