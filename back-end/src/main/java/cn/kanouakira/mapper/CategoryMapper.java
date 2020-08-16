package cn.kanouakira.mapper;

import cn.kanouakira.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 标签的分类表 Mapper 接口
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-21
 */
@Mapper
public interface CategoryMapper {
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
