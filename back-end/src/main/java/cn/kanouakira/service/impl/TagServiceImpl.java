package cn.kanouakira.service.impl;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.common.utils.PageUtils;
import cn.kanouakira.entity.Tag;
import cn.kanouakira.entity.User;
import cn.kanouakira.mapper.TagMapper;
import cn.kanouakira.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public PageResult selectTagPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     */
    public PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> userPage = tagMapper.selectTagPage();
        return new PageInfo(userPage);
    }

    @Override
    public List<Tag> selectAllTag() {
        return tagMapper.selectAllTag();
    }

    @Override
    public Tag selectTagByName(String tagName) {
        return tagMapper.selectTagByName(tagName);
    }

    @Override
    public boolean saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public boolean removeTagById(Long id) {
        return tagMapper.removeTagById(id);
    }
}
