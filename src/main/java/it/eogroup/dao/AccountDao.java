package it.eogroup.dao;

import it.eogroup.domain.Account;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
 * 用户账户数据持久层
 */

@Repository
public interface AccountDao {
    //查询所有账户信息
    @Select("select * from account")
    public List<Account> findAll();
}
