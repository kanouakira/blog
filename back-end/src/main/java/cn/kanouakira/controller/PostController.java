package cn.kanouakira.controller;


import cn.kanouakira.common.annotation.UserLoginToken;
import cn.kanouakira.common.lang.PageRequest;
import cn.kanouakira.common.lang.Result;
import cn.kanouakira.entity.Post;
import cn.kanouakira.entity.TagRelationship;
import cn.kanouakira.service.PostService;
import cn.kanouakira.service.TagRelationshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 博客文章表 前端控制器
 * </p>
 *
 * @author kanouakira
 * @since 2020-06-12
 */
@RestController
@Api(description = "文章操作", tags = {"文章操作接口"})
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    TagRelationshipService tagRelationshipService;

    @Autowired
    RedisTemplate redisTemplate;
    public static final String VIEW_RANK = "viewRank";

    /**
     * 查询所有文章
     * @param current_page 分页当前页
     * @param per_page 分页每页数量
     * @param tag 文章标签
     * @param manage 是否后台查询
     * @param search 是否搜索
     * @return
     */
    @ApiOperation("查询所有文章分页")
    @GetMapping
    public Result getPosts(@RequestParam("page") Integer current_page,
                           @RequestParam("per_page") Integer per_page,
                           @RequestParam(value = "tag", defaultValue = "",required = false) String tag,
                           @RequestParam(value = "manage", defaultValue = "",required = false) String manage,
                           @RequestParam(value = "search", defaultValue = "",required = false) String search){

        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        PageRequest pageRequest = new PageRequest(current_page, per_page);
        return Result.succ(postService.selectPostPage(pageRequest,(Long) request.getAttribute("userId"),tag,manage,search));
//        Page<Post> page = new Page<>(current_page,per_page);
//        IPage<Post> mapIPage;
//        mapIPage = postService.selectCustomPage(page, (Long) request.getAttribute("userId"), tag, manage,search);
//
//        Map<String , Object> data = new HashMap<>();
//        data.put("per_page", per_page);
//        data.put("total", mapIPage.getTotal());
//        data.put("data", mapIPage.getRecords());
//        return Result.succ(200, "操作成功", data);
    }

    /**
     * 根据id查找文章
     * @param id
     * @return
     */
    @ApiOperation("根据id查找文章")
    @GetMapping("/{id}")
    public Result getPost(@PathVariable("id") Long id){
        Post post = postService.selectPostById(id);
        return Result.succ(200, "操作成功", post);
    }

    /**
     * 新增一个文章
     * @param post
     * @return
     */
    @UserLoginToken
    @ApiOperation("新增一个文章")
    @PostMapping
    public Result createPost(@RequestBody Post post){
        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        post.setAuthorId((Long) request.getAttribute("userId"));
        return Result.succ(postService.savePost(post) ? post.getId() : null);
    }
    //todo 尚未验证接口是否可用

    /**
     * 根据id修改文章
     * @param id
     * @param post
     * @return
     */
    @UserLoginToken
    @ApiOperation("根据id修改文章")
    @PutMapping("/{id}")
    public Result updatePost(@PathVariable("id") Long id,@RequestBody Post post){
        //得到servlet中的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        // 仅作者可以修改文章内容
//        if (!request.getAttribute("userId").equals(post.getAuthorId())){
//            return Result.fail("无修改权限");
//        }
        post.setId(id);
        if(post.getOnlySelfVisible()){
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            zSetOperations.remove(VIEW_RANK, post.getId());
        }
        return Result.succ(postService.updatePost(post));
    }

    /**
     * 根据id增加浏览数
     * @param id
     * @return
     */
    @ApiOperation("根据id增加浏览数")
    @PutMapping("/{id}/addViews")
    public Result addPostView(@PathVariable("id") Long id) {
        Post post = postService.selectPostById(id);
        post.setViews(post.getViews() + 1);
        if(!post.getOnlySelfVisible()){
            ZSetOperations zSetOperations = redisTemplate.opsForZSet();
            if (zSetOperations.rank(VIEW_RANK, post.getId()) == null){
                zSetOperations.add(VIEW_RANK, post.getId(), 1);
            }else{
                zSetOperations.incrementScore(VIEW_RANK, post.getId(), 1);
            }
        }
        return Result.succ(postService.updatePost(post));
    }


    /**
     * 根据id添加标签
     * @param params
     * @return
     */
    @ApiOperation("根据id添加标签")
    @UserLoginToken
    @PostMapping("/{id}/tags")
    public Result addPostTag(@PathVariable("id") Long id, @RequestBody List<Long> params) {
        for(Long tagId : params){
            TagRelationship tagRelationship = new TagRelationship();
            tagRelationship.setPostId(id);
            tagRelationship.setTagId(tagId);
            if (!tagRelationshipService.save(tagRelationship)){
                return Result.fail("添加标签出错");
            }
        }
        return Result.succ("操作成功");
    }

    /**
     * 根据post_id更新标签
     * @param id
     * @param params
     * @return
     */
    @ApiOperation("根据post_id更新标签")
    @UserLoginToken
    @PutMapping("/{id}/tags")
    public Result updatePostTag(@PathVariable("id") Long id, @RequestBody List<Long> params) {
        List<TagRelationship> tagRelationships = tagRelationshipService.findTagRelationshipsByPostId(id);
        for(TagRelationship tagRelationship : tagRelationships){
            tagRelationshipService.removeById(tagRelationship.getId());
        }
        for(Long tagId : params){
            TagRelationship tagRelationship = new TagRelationship();
            tagRelationship.setPostId(id);
            tagRelationship.setTagId(tagId);
            if (!tagRelationshipService.save(tagRelationship)){
                return Result.fail("添加标签出错");
            }
        }
        return Result.succ("操作成功");
    }

    /**
     * 根据post_id获取标签
     * @param id
     * @return
     */
    @ApiOperation("根据post_id获取标签")
    @UserLoginToken
    @GetMapping("/{id}/tags")
    public Result getPostTags(@PathVariable("id") Long id){
        List<TagRelationship> tagRelationships = tagRelationshipService.findTagRelationshipsByPostId(id);
        List<Long> tagIds = new ArrayList<>();
        for(TagRelationship tagRelationship : tagRelationships){
            tagIds.add(tagRelationship.getTagId());
        }
        return Result.succ(tagIds);
    }


    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @UserLoginToken
    @ApiOperation("根据id删除文章")
    @DeleteMapping("/{id}")
    public Result deletePost(@PathVariable("id") Long id){
        Boolean result = postService.removePostById(id);
        return Result.succ(result);
    }

    /**
     * 获取文章热度排行榜
     * @return
     */
    @ApiOperation("获取文章热度排行榜")
    @GetMapping("/rank")
    public Result getPostRank(){
        List<Post> posts = new ArrayList<>();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Set set = zSetOperations.reverseRange(VIEW_RANK, 0, 4);
        set.forEach(post_id -> {
            posts.add(postService.selectPostById((Long) post_id));
        });
        return Result.succ(posts);
    }

    /**
     * 每周一凌晨清空文章热度排行榜
     */
    @Scheduled(cron = "0 0 0 ? * MON") // 秒（0-59），分（0-59），时（0-23），日期天/日（1-31），月份）（1-12），星期（1-7,1表示星晴天，7表示星期六），年（可以缺省。取值范围是1970-2099）
    public void clearRankScore(){
        redisTemplate.delete(VIEW_RANK);
//        System.out.println("clear");
    }
}
