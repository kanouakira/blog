package cn.kanouakira.mapper;

import cn.kanouakira.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-13
 */
@Mapper
public interface CommentMapper {
    /**
     * 自定义sql分页
     * @return
     */
    List<Comment> selectCommentPage();

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
}
