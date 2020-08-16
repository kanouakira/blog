package cn.kanouakira.service.impl;

import cn.kanouakira.entity.Tag;
import cn.kanouakira.mapper.TagMapper;
import cn.kanouakira.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectTagPage() {
        return tagMapper.selectTagPage();
    }
}
