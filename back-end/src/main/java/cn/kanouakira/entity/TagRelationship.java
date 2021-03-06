package cn.kanouakira.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 标签关系表
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TagRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 文章id
     */
    private Long postId;

    /**
     * 标签id
     */
    private Long tagId;


}
