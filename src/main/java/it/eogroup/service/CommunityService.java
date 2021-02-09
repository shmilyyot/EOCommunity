package it.eogroup.service;

import it.eogroup.domain.Community;
import it.eogroup.domain.Post;

import java.util.List;

public interface CommunityService {

    //获得最热门的板块
    List<Community> getTopCommunity();

    //获得最热门的帖子
    List<Post> getTopPosts();

    //获得对应板块
    Community getCommunity(Integer communityId);

    //获得板块名字
    String getCommunityName(Integer communityId);

    //获得板块所有帖子
    List<Post> postFindAll(Integer page,Integer size,Integer communityId);

    //获得发帖人的名字
    String getPostName(Integer accountId);
}
