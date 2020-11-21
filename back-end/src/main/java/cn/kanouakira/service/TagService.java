package cn.kanouakira.service;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-14
 */
public interface TagService {
    /**
     * 自定义sql分页
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest
     * @return PageResult
     */
    PageResult selectTagPage(PageRequest pageRequest);

    /**
     * 查询所有标签不分页
     * @return
     */
    List<Tag> selectAllTag();

    /**
     * 根据标签名返回标签
     * @param tagName
     * @return
     */
    Tag selectTagByName(String tagName);

    /**
     * 新增标签
     * @param tag
     * @return
     */
    boolean saveTag(Tag tag);

    /**
     * 更新标签
     * @param tag
     * @return
     */
    boolean updateTag(Tag tag);

    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    boolean removeTagById(Long id);
}
