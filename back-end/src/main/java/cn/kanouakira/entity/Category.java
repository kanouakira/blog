package cn.kanouakira.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 标签的分类表
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名
     */
    private String category;

    /**
     * 该分类下所有的标签
     */
    private List<Tag> tags;

}
