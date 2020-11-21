package cn.kanouakira.service;

import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.PageResult;
import cn.kanouakira.entity.Post;

/**
 * <p>
 * 博客文章表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-12
 */
public interface PostService {

    /**
     * 自定义sql分页
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest
     * @return PageResult
     */
    PageResult selectPostPage(PageRequest pageRequest, Long author_id,
                              String tag,String manage,String search);

    /**
     * 保存文章
     * @param post
     * @return
     */
    boolean savePost(Post post);

    /**
     * 根据id返回一篇文章
     * @param id
     * @return
     */
    Post selectPostById(Long id);

    /**
     * 根据id更新一篇文章
     * @param post
     * @return
     */
    Integer updatePost(Post post);

    /**
     * 根据id删除一篇文章
     * @param id
     * @return
     */
    Boolean removePostById(Long id);
}
