package cn.kanouakira.controller;

import cn.kanouakira.common.annotation.UserLoginToken;
import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.lang.Result;
import cn.kanouakira.entity.Tag;
import cn.kanouakira.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-14
 */
@RestController
@Api(description = "标签操作", tags = {"标签操作接口"})
@RequestMapping("/tags")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 查询所有标签分页
     * @param current_page
     * @param per_page
     * @return
     */
    @ApiOperation("查询所有标签分页")
    @GetMapping
    public Result getTags(@RequestParam(value = "page",defaultValue = "", required = false) Integer current_page,
                          @RequestParam(value = "per_page",defaultValue = "",required = false) Integer per_page){
        if (current_page == null){
            return Result.succ(tagService.selectAllTag());
        }
        PageRequest pageRequest = new PageRequest(current_page, per_page);
        return Result.succ(200, "操作成功", tagService.selectTagPage(pageRequest));
    }

    /**
     * 新增标签
     * @param tag
     * @return
     */
    @ApiOperation("新增标签")
    @PostMapping()
    public Result createTag(@RequestBody Tag tag){
        if (tag.getName() == ""){
            return Result.fail("标签名不能为空");
        }
        if (tagService.selectTagByName(tag.getName()) != null){
            return Result.fail("标签已存在");
        }
        return Result.succ(tagService.saveTag(tag));
    }

    /**
     * 根据id更新标签
     * @param id
     * @param categoryId
     * @return
     */
    @UserLoginToken
    @ApiOperation("根据id更新标签")
    @PutMapping("/{id}")
    public Result updateTag(@PathVariable("id") Long id,
                            @RequestParam("categoryId") Long categoryId){
        Tag tag = new Tag();
        tag.setId(id);
        tag.setCategoryId(categoryId);
        return Result.succ(tagService.updateTag(tag));
    }

    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    @UserLoginToken
    @DeleteMapping("/{id}")
    public Result deleteTag(@PathVariable("id") Long id){
        return Result.succ(tagService.removeTagById(id));
    }
}
