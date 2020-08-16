package cn.kanouakira.service;

import cn.kanouakira.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @return
     */
    List<Post> selectPostPage(@Param("id") Long author_id,
                              @Param("tag") String tag,
                              @Param("manage")String manage,
                              @Param("search")String search);

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
}
