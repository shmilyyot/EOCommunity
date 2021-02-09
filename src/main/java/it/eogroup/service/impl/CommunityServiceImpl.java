package it.eogroup.service.impl;

import it.eogroup.dao.CommunityDao;
import it.eogroup.domain.Community;
import it.eogroup.domain.Post;
import it.eogroup.domain.PostCommunity;
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
    public List<PostCommunity> getTopPosts() {
        logger.info("成功返回了热门帖子");
        return communityDao.getTopPosts();
    }

    @Override
    public Community getCommunity(Integer communityId) {
        logger.info("返回对应社区数据");
        return communityDao.getCommunity(communityId);
    }

    @Override
    public String getCommunityName(Integer communityId) {
        return communityDao.getCommunityName(communityId);
    }


}
