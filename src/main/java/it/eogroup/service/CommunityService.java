package it.eogroup.service;

import it.eogroup.domain.*;
import java.time.LocalDateTime;
import java.util.List;

public interface CommunityService {

    //获得最热门的板块
    List<Community> getTopCommunity();

    //获得所有社区
    List<Community> getAllCommunities();

    //获得最热门的帖子
    List<Post> getTopPosts();

    //获得对应板块
    Community getCommunity(Integer communityId);

    //获得板块名字
    String getCommunityName(Integer communityId);

    //获得板块所有帖子
    List<Post> postFindAll(Integer page,Integer size,Integer communityId);

    //获得发帖人的名字
    String getPostName(Integer postId);

    //获得帖子所有评论
    List<Comment> commentFindAll(Integer page, Integer size, Integer postId);

    //获得帖子
    Post getPost(Integer postId);

    //查找所有评论+账户
    List<CommentAccount> commentAccountFindAll(Integer page, Integer size, Integer postId);

    //插入收藏夹
    Boolean insertFavPost(Integer accountId, String url, String title);

    //找到账户所有帖子
    List<Post> accountPostFindAll(Integer page, Integer size,Integer accountId);

    //删除收藏夹
    void deleteFavPost(Integer favId);

    //插入评论
    void insertComment(Comment comment);

    //插入帖子
    void insertPost(Post post);

    //根据发帖时间找回帖子
    Post getPostByTime(LocalDateTime localDateTime);

    Post getPostById(Integer accountId);

    //根据帖子id找到发帖人的id
    Integer getAccountIdByPostId(Integer postId);

    //查找未读评论
    List<CommentAccount> findAllMessage(Integer accountId);

    //查找未读评论+帖子
    List<CommentAccountPost> findUnReadMessagePost(Integer page, Integer size,Integer accountId);

    //评论标为已读
    void readMessage(Integer commentId);

    //所有评论已读
    void readAllMessage(Integer accountId);

}
