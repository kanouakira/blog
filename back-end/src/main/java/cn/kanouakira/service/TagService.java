package cn.kanouakira.service;

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
     * @return
     */
    List<Tag> selectTagPage();
}
