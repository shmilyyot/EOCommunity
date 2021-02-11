package it.eogroup.service.impl;

import com.github.pagehelper.PageHelper;
import it.eogroup.dao.CommunityDao;
import it.eogroup.domain.Comment;
import it.eogroup.domain.CommentAccount;
import it.eogroup.domain.Community;
import it.eogroup.domain.Post;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("communityService")
@Transactional //声明式事务
public class CommunityServiceImpl implements CommunityService {

    @Resource
    private CommunityDao communityDao;
    private static final Logger logger = LogManager.getLogger(CommunityServiceImpl.class);

    @Override
    //获得最热门的社区
    public List<Community> getTopCommunity() {
        logger.info("返回最热社区列表");
        return communityDao.getTopCommunity();
    }

    @Override
    public List<Post> getTopPosts() {
        logger.info("返回了热门帖子");
        return communityDao.getTopPosts();
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

}
