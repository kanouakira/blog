package cn.kanouakira.service.impl;

import cn.kanouakira.entity.Post;
import cn.kanouakira.mapper.PostMapper;
import cn.kanouakira.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public List<Post> selectPostPage(Long author_id, String tag, String manage, String search) {
        return postMapper.selectPostPage(author_id, tag, manage, search);
    }

    @Override
    public Post selectPostById(Long id) {
        return postMapper.selectPostById(id);
    }

    @Override
    public Integer updatePost(Post post) {
        return postMapper.updatePost(post);
    }
}
