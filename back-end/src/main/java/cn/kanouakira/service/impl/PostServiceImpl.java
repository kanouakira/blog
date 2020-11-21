package cn.kanouakira.service.impl;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.utils.PageUtils;
import cn.kanouakira.entity.Post;
import cn.kanouakira.mapper.PostMapper;
import cn.kanouakira.service.PostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostMapper postMapper;

    @Override
    public PageResult selectPostPage(PageRequest pageRequest,Long author_id, String tag, String manage, String search) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest,author_id,tag,manage,search));
    }

    /**
     * 调用分页插件完成分页
     */
    public PageInfo<Post> getPageInfo(PageRequest pageRequest,Long author_id, String tag, String manage, String search) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Post> postPage = postMapper.selectPostPage(author_id,tag,manage,search);
        return new PageInfo(postPage);
    }

    @Override
    public boolean savePost(Post post) {
        return postMapper.savePost(post);
    }

    @Override
    public Post selectPostById(Long id) {
        return postMapper.selectPostById(id);
    }

    @Override
    public Boolean removePostById(Long id) {
        return postMapper.removePostById(id);
    }

    @Override
    public Integer updatePost(Post post) {
        return postMapper.updatePost(post);
    }
}
