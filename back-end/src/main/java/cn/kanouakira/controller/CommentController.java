package cn.kanouakira.controller;


import cn.kanouakira.common.annotation.UserLoginToken;
import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.Result;
import cn.kanouakira.entity.Comment;
import cn.kanouakira.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-13
 */

@RestController
@Api(description = "评论操作", tags = {"评论操作接口"})
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 查询所有评论
     * @return
     */
    @ApiOperation("查询所有评论分页")
    @GetMapping
    public Result getComments(@RequestParam("page") Integer current_page,
                              @RequestParam("per_page") Integer per_page){
        PageRequest pageRequest = new PageRequest(current_page, per_page);
        return Result.succ(commentService.selectCommentPage(pageRequest));
    }

    /**
     * 根据id查找评论
     * @param id
     * @return
     */
    @ApiOperation("根据id查找评论")
    @GetMapping("/{id}")
    public Result getComment(@PathVariable("id") Long id){
        Comment comment = commentService.selectCommentById(id);
        return Result.succ(200, "操作成功", comment);
    }

    /**
     * 新增一个评论
     * @param comment
     * @return
     */
    @UserLoginToken
    @ApiOperation("新增一个评论")
    @PostMapping
    public Result createPost(@RequestBody Comment comment){
        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        comment.setUserId((Long) request.getAttribute("userId"));
        return Result.succ(commentService.saveComment(comment));
    }

    /**
     * 根据id修改评论
     * @param id
     * @param comment
     * @return
     */
    @UserLoginToken
    @PutMapping("/{id}")
    public Result updatePost(@PathVariable("id") Long id, @RequestBody Comment comment){
        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 仅作者可以修改评论内容
//        if (!request.getAttribute("userId").equals(comment.getUserId())){
//            return Result.fail("无修改权限");
//        }
        comment.setId(id);
        return Result.succ(commentService.updateComment(comment));
    }

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    @UserLoginToken
    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable("id") Long id){
        return Result.succ(commentService.removeCommentById(id));
    }
}
