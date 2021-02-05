package it.eogroup.service;

import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import java.util.List;

/*
* 账户业务层
* Servlet和Dao之间的缓冲层，解耦，是Dao层变化不会影响上层服务层
* */
public interface AccountService {

    //查询所有账户信息
    List<Account> findAll();

    //用户注册
    Boolean CreateAccount(Account account);

    //获得用户
    Account getAccount(String username);

    Role getRole(String username);

}
