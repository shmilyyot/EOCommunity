package it.eogroup.service.impl;

import it.eogroup.dao.CommunityDao;
import it.eogroup.domain.Community;
import it.eogroup.domain.Invatation;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<Community> list = communityDao.getTopCommunity();
        for(Community community:list) System.out.println(community);
        logger.info("返回最热社区列表");
        return communityDao.getTopCommunity();
    }

    @Override
    //获得最热门的帖子
    public List<Invatation> getTopInvatations() {
        return null;
    }
}
