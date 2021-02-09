package it.eogroup.dao;

import it.eogroup.domain.Community;
import it.eogroup.domain.Post;
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
    String getPostName(Integer accountId);
}
