package cn.kanouakira.service;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.entity.Comment;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-13
 */
public interface CommentService {
    /**
     *
     * 自定义sql分页
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest
     * @return
     */
    PageResult selectCommentPage(PageRequest pageRequest);

    /**
     * 根据id返回评论
     * @param id
     * @return
     */
    Comment selectCommentById(Long id);

    /**
     * 根据id更新评论
     * @param comment
     * @return
     */
    Integer updateComment(Comment comment);

    /**
     * 保存新的评论
     * @param comment
     * @return
     */
    boolean saveComment(Comment comment);

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    boolean removeCommentById(Long id);
}
