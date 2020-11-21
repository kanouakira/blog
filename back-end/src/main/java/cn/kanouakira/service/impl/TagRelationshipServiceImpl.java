package cn.kanouakira.service.impl;

import cn.kanouakira.entity.TagRelationship;
import cn.kanouakira.mapper.TagRelationshipMapper;
import cn.kanouakira.service.TagRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagRelationshipServiceImpl implements TagRelationshipService {
    @Autowired
    private TagRelationshipMapper tagRelationshipMapper;

    @Override
    public boolean save(TagRelationship tagRelationship) {
        return tagRelationshipMapper.save(tagRelationship);
    }

    @Override
    public boolean removeById(Long id) {
        return tagRelationshipMapper.removeById(id);
    }

    @Override
    public List<TagRelationship> findTagRelationshipsByPostId(Long post_id) {
        return tagRelationshipMapper.findTagRelationshipsByPostId(post_id);
    }
}
