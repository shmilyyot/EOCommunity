package it.eogroup.dao;

import it.eogroup.domain.Comment;
import it.eogroup.domain.CommentAccount;
import it.eogroup.domain.Community;
import it.eogroup.domain.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunityDao {

    //获得最热门的社区
    List<Community> getTopCommunity();

    //获得对应社区
    @Select("SELECT * FROM community WHERE communityId = #{communityId}")
    Community getCommunity(Integer communityId);

    //获得最热门的帖子
    List<Post> getTopPosts();

    //获得板块名字
    @Select("SELECT community.communityName FROM community WHERE communityId = #{communityId}")
    String getCommunityName(Integer communityId);

    //获得板块所有帖子
    List<Post> postFindAll(Integer communityId);

    //获得发帖人的名字
    String getPostName(Integer postId);

    //获得帖子所有评论
    List<Comment> commentFindAll(Integer postId);

    //根据帖子id找帖子
    @Select("SELECT * FROM post WHERE post.`postId` = #{postId}")
    Post getPost(Integer postId);

    List<CommentAccount> commentAccountFindAll(Integer postId);

    //添加到收藏夹
    void insertFavPost(@Param("accountId") Integer accountId, @Param("url") String url, @Param("title") String title);

    //根据账户id获取所有帖子
    List<Post> accountPostFindAll(Integer accountId);

    //删除收藏夹
    @Delete("DELETE FROM favPost WHERE favPost.`favId` = #{favId}")
    void deleteFavPost(Integer favId);

    //查找收藏夹是否有指定帖子
    @Select("SELECT * FROM favpost WHERE favpost.`url` = #{url}")
    Post searchPostExist(String url);

}
