package it.eogroup.service.impl;

import com.github.pagehelper.PageHelper;
import it.eogroup.dao.CommunityDao;
import it.eogroup.domain.*;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("communityService")
@Transactional //声明式事务
public class CommunityServiceImpl implements CommunityService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private CommunityDao communityDao;
    private static final Logger logger = LogManager.getLogger(CommunityServiceImpl.class);

    @Override
    //获得最热门的社区
    public List<Community> getTopCommunity() {
        logger.info("返回最热社区列表");
        List<Community> communities = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().reverseRangeWithScores("community_set", 0, 2);
        if (set == null)
        {
            logger.info("[Redis] [error]: cache doesn't exist, search database");
            return communityDao.getTopCommunity();
        }
        for (ZSetOperations.TypedTuple<Object> typedTuple : set) {
            Integer communityId = (Integer) typedTuple.getValue();
            Community community = communityDao.getCommunity(communityId);
            communities.add(community);
        }
        return communities;
    }

    @Override
    public List<Community> getAllCommunities() {
        logger.info("返回所有社区");
        return communityDao.getAllCommunities();
    }

    @Override
    public List<Post> getTopPosts() {
        logger.info("返回了热门帖子");
        List<Post> posts = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> set = redisTemplate.opsForZSet().reverseRangeWithScores("post_set", 0, 9);
        if (set == null)
        {
            logger.info("[Redis] [error]: cache doesn't exist, search database");
            return communityDao.getTopPosts();
        }
        for (ZSetOperations.TypedTuple<Object> typedTuple : set) {
            Integer postId = (Integer) typedTuple.getValue();
            Post post = communityDao.getPost(postId);
            posts.add(post);
        }
        return posts;
    }

    @Override
    public Community getCommunity(Integer communityId) {
        logger.info("返回对应板块");
        return communityDao.getCommunity(communityId);
    }

    @Override
    public String getCommunityName(Integer communityId) {
        logger.info("返回了板块名字");
        return communityDao.getCommunityName(communityId);
    }

    @Override
    public List<Post> postFindAll(Integer page,Integer size,Integer communityId) {
        logger.info("帖子总数分页操作");
        PageHelper.startPage(page,size);
        return communityDao.postFindAll(communityId);
    }

    @Override
    public String getPostName(Integer postId) {
        logger.info("返回用户"+postId+"的用户名");
        return communityDao.getPostName(postId);
    }

    @Override
    public List<Comment> commentFindAll(Integer page, Integer size, Integer postId) {
        logger.info("评论总数分页操作");
        PageHelper.startPage(page,size);
        return communityDao.commentFindAll(postId);
    }

    @Override
    public Post getPost(Integer postId) {
        logger.info("返回帖子");
        return communityDao.getPost(postId);
    }

    @Override
    public List<CommentAccount> commentAccountFindAll(Integer page, Integer size, Integer postId) {
        PageHelper.startPage(page,size);
        return communityDao.commentAccountFindAll(postId);
    }

    @Override
    public Boolean insertFavPost(Integer accountId,String url, String title) {
        if(communityDao.searchPostExist(url,accountId) != null){
            logger.error("此帖子已存在于收藏夹");
            return false;
        }
        communityDao.insertFavPost(accountId,url,title);
        logger.info("插入收藏夹成功");
        return true;
    }

    @Override
    public List<Post> accountPostFindAll(Integer page, Integer size,Integer accountId) {
        logger.info("返回账户所有帖子");
        PageHelper.startPage(page,size);
        return communityDao.accountPostFindAll(accountId);
    }

    @Override
    public void deleteFavPost(Integer favId) {
        communityDao.deleteFavPost(favId);
        logger.info("删除收藏帖子");
    }

    @Override
    public void insertComment(Comment comment) {
        logger.info("插入评论");
        Integer floor = communityDao.getCommentFloor(comment.getPostId());
        if(floor == 0){
            comment.setCommentFloor("楼主");
        }else{
            comment.setCommentFloor((floor+1)+"楼");
        }
        communityDao.insertComment(comment);
        updateRankOfPost(comment.getPostId());
    }

    @Override
    public void insertPost(Post post) {
        logger.info("插入帖子");
        communityDao.insertPost(post);
        updateRankOfCommunity(post.getCommunityId());
    }

    public void updateRankOfCommunity(Integer communityId)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        long timeOut = (calendar.getTimeInMillis()-System.currentTimeMillis()) / 1000; //晚上十二点与当前时间的毫秒差
        redisTemplate.expire("community_set",timeOut, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().incrementScore("community_set", communityId, 1);
    }

    public void updateRankOfPost(Integer postId)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.MILLISECOND,0);
        long timeOut = (calendar.getTimeInMillis()-System.currentTimeMillis()) / 1000; //晚上十二点与当前时间的毫秒差
        redisTemplate.expire("post_set",timeOut, TimeUnit.SECONDS);
        redisTemplate.opsForZSet().incrementScore("post_set", postId, 1);
    }

    @Override
    public Post getPostByTime(LocalDateTime localDateTime) {
        logger.info("找回帖子");
        return communityDao.getPostByTime(localDateTime);
    }

    @Override
    public Post getPostById(Integer accountId) {
        return communityDao.getPostById(accountId);
    }

    @Override
    public Integer getAccountIdByPostId(Integer postId) {
        logger.info("返回发帖人的id");
        return communityDao.getAccountIdByPostId(postId);
    }

    @Override
    public List<CommentAccount> findAllMessage(Integer accountId) {
        logger.info("返回该账户所有未读消息");
        return communityDao.findUnReadMessage(accountId,false);
    }

    @Override
    public List<CommentAccountPost> findUnReadMessagePost(Integer page, Integer size,Integer accountId) {
        logger.info("返回该账户所有未读消息");
        PageHelper.startPage(page,size);
        return communityDao.findUnReadMessagePost(accountId,false);
    }

    @Override
    public void readMessage(Integer commentId) {
        logger.info("尝试标为已读消息");
        communityDao.readMessage(commentId,true);
    }

    @Override
    public void readAllMessage(Integer accountId) {
        communityDao.readAllMessage(accountId,true);
        logger.info("所有未读评论执行已读");
    }

    @Override
    public Integer getCommentAccountId(Integer commentId) {
        logger.info("根据评论id找回账户id");
        return communityDao.getCommentAccountId(commentId);
    }

    @Override
    public List<Post> getRelatedPosts(Integer page, Integer size,String keyword) {
        logger.info("返回模糊查找帖子的集合");
        PageHelper.startPage(page,size);
        return communityDao.getRelatedPosts(keyword);
    }


}
