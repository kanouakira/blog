package cn.kanouakira.service.impl;

import cn.kanouakira.entity.Comment;
import cn.kanouakira.mapper.CommentMapper;
import cn.kanouakira.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectCommentPage() {
        return commentMapper.selectCommentPage();
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
