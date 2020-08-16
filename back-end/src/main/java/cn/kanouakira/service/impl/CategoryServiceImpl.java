package cn.kanouakira.service.impl;

import cn.kanouakira.entity.Category;
import cn.kanouakira.mapper.CategoryMapper;
import cn.kanouakira.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryPage() {
        return categoryMapper.selectCategoryPage();
    }

    @Override
    public Category selectCategoryById(Long id) {
        return categoryMapper.selectCategoryById(id);
    }
}
