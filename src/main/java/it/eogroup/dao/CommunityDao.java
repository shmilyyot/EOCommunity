package it.eogroup.dao;

import it.eogroup.domain.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommunityDao {

    //获得最热门的社区
    List<Community> getTopCommunity();

    //获得所有社区
    @Select("SELECT * FROM community")
    List<Community> getAllCommunities();

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

    //插入帖子评论
    void insertComment(Comment comment);

    //插入帖子
    void insertPost(Post post);

    //根据发帖时间找回帖子
    @Select("select * from post where post.`postTime` = #{localDateTime}")
    Post getPostByTime(LocalDateTime localDateTime);

    //根据id找回帖子
    @Select("select * from post where post.`accountId` = #{accountId}")
    Post getPostById(Integer accountId);

    //根据帖子id找到发帖人的id(为了定位评论的时候回复的是哪个人)
    @Select("SELECT accountId FROM post WHERE post.`postId` = #{postId}")
    Integer getAccountIdByPostId(Integer postId);

    //插入留言板
    void insertMessageBoard(MessageBoard messageBoard);

    //查找留言板
    @Select("SELECT * FROM messageBoard ORDER BY messageBoard.`messageTime` DESC")
    List<MessageBoard> findAllMessage();

    //查找未读评论
    List<CommentAccount> findUnReadMessage(@Param("accountId") Integer accountId,@Param("commentStatus") Boolean commentStatus);

    //查找未读评论+帖子
    List<CommentAccountPost> findUnReadMessagePost(@Param("accountId") Integer accountId,@Param("commentStatus") Boolean commentStatus);

    //评论标为已读
    void readMessage(@Param("commentId") Integer commentId,@Param("commentStatus") Boolean commentStatus);
}
