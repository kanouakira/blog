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
