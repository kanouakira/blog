package cn.kanouakira.service;

import cn.kanouakira.entity.Category;

import java.util.List;

/**
 * <p>
 * 标签的分类表 服务类
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-21
 */
public interface CategoryService {
    /**
     * 自定义分页sql
     * @return
     */
    List<Category> selectCategoryPage();

    /**
     * 根据Id获取标签名
     * @return
     */
    Category selectCategoryById(Long id);
}
