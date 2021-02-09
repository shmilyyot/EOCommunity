package it.eogroup.dao;

import it.eogroup.domain.Community;
import it.eogroup.domain.PostCommunity;
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
    List<PostCommunity> getTopPosts();

    //获得板块名字
    @Select("SELECT community.communityName FROM community WHERE communityId = #{communityId}")
    String getCommunityName(Integer communityId);
}
