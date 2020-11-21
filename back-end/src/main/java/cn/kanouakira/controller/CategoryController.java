package cn.kanouakira.controller;

import cn.kanouakira.common.lang.Result;
import cn.kanouakira.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签的分类表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-21
 */
@RestController
@Api(description = "分类操作", tags = {"分类操作接口"})
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ApiOperation("查询所有分类")
    @GetMapping()
    public Result getCategories(){
        return Result.succ(categoryService.selectCategoryPage());
    }
}
