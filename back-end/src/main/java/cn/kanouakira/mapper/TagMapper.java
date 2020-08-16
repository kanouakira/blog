package cn.kanouakira.mapper;

import cn.kanouakira.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-14
 */
@Mapper
public interface TagMapper{

    /**
     * 自定义sql分页
     * @return
     */
    List<Tag> selectTagPage();

}
