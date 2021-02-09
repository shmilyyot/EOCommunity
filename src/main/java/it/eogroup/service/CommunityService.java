package it.eogroup.service;

import it.eogroup.domain.Community;
import it.eogroup.domain.PostCommunity;

import java.util.List;

public interface CommunityService {

    //获得最热门的社区
    List<Community> getTopCommunity();

    //获得最热门的帖子
    List<PostCommunity> getTopPosts();

    //获得对应社区
    Community getCommunity(Integer communityId);

    //获得板块名字
    String getCommunityName(Integer communityId);
}
