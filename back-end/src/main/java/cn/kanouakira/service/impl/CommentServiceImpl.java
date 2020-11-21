package cn.kanouakira.service.impl;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.utils.PageUtils;
import cn.kanouakira.entity.Comment;
import cn.kanouakira.entity.User;
import cn.kanouakira.mapper.CommentMapper;
import cn.kanouakira.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public PageResult selectCommentPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    @Override
    public boolean saveComment(Comment comment) {
        return commentMapper.saveComment(comment);
    }

    @Override
    public boolean removeCommentById(Long id) {
        return commentMapper.removeCommentById(id);
    }

    /**
     * 调用分页插件完成分页
     */
    public PageInfo<Comment> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Comment> userPage = commentMapper.selectCommentPage();
        return new PageInfo(userPage);
    }

    @Override
    public Comment selectCommentById(Long id) {
        return commentMapper.selectCommentById(id);
    }


    @Override
    public Integer updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }
}
