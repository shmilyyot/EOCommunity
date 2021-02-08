package it.eogroup.dao;

import it.eogroup.domain.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDao {

    //获得最热门的社区
    List<Community> getTopCommunity();
}
