package cn.kanouakira.service;

import cn.kanouakira.entity.TagRelationship;

import java.util.List;

public interface TagRelationshipService {
    /**
     * 保存对应关系
     * @param tagRelationship
     * @return
     */
    boolean save(TagRelationship tagRelationship);

    /**
     * 根据id删除对应关系
     * @param id
     * @return
     */
    boolean removeById(Long id);

    /**
     * 根据postId返回所有对应关系
     * @param post_id
     * @return
     */
    List<TagRelationship> findTagRelationshipsByPostId(Long post_id);
}
