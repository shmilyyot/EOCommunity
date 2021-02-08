package it.eogroup.dao;

import it.eogroup.domain.Community;
import it.eogroup.domain.Invatation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDao {

    //获得最热门的社区
    List<Community> getTopCommunity();

    //获得最热门的帖子
//    List<Invatation> getTopInvatations();

    //获得对应社区
    @Select("SELECT * FROM community WHERE communityId = #{communityId}")
    Community getCommunity(Integer communityId);
}
