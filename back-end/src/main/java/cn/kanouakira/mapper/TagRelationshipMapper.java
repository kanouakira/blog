package cn.kanouakira.mapper;

import cn.kanouakira.entity.TagRelationship;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagRelationshipMapper {
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
