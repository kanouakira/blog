package cn.kanouakira.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 评论人
     */
    private Long userId;

    /**
     * 被评论文章
     */
    private Long postId;

    /**
     * 评论状态
     */
    private Integer status;

    /**
     * 评论内容
     */
    private String body;

    /**
     * 评论级别，回复文章是一级其他是二级
     */
    private Integer commentLevel;

    /**
     * 父级评论id
     */
    private Long parentCommentId;

    /**
     * 被回复评论的id
     */
    private Long replyCommentId;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 该评论的用户
     */
    private User user;

    /**
     * 回复的评论
     */
    private Comment replyComment;

    /**
     * 子评论
     */
    private List<Comment> childComments;

}
