package it.eogroup.service;

import it.eogroup.domain.Community;
import it.eogroup.domain.Invatation;
import java.util.List;

public interface CommunityService {

    //获得最热门的社区
    List<Community> getTopCommunity();

    //获得最热门的帖子
    List<Invatation> getTopInvatations();

    //获得对应社区
    Community getCommunity(Integer communityId);
}
