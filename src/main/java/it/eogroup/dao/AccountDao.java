package it.eogroup.dao;

import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import net.sf.jsqlparser.schema.Sequence;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 用户账户数据持久层
 * 回头全部改注解，取消xml
 */
@Repository
public interface AccountDao {
    //查询所有账户信息
    @Select("select * from account")
    List<Account> findAll();

    //插入新账户信息
    void insertAccount(Account account);

    //根据用户名查找账户
    Account accountExistByName(String accountName);

    //根据用户id查找账户
    Account accountExistById(Integer accountId);

    //根据用户id查找角色
    @Select("SELECT * FROM ACCOUNTROLE WHERE accountId=#{accountId}")
    Role roleExistById(Integer accountId);

    //插入账户角色
    void insertRole(Integer accountId);

    //查找用户ID
    @Select("SELECT accountId FROM ACCOUNT where accountName=#{accountName}")
    Integer findAccountId(String accountName);

    //查找用户角色
    @Select("SELECT accountRole FROM ACCOUNTROLE where accountId in (select accountId from account where accountName = #{accountName})")
    String findAccountRole(String accountName);

    //更新账户信息
    void updateAccount(@Param("account") Account account);

    //更新密码
    void updatePassword(@Param("account") Account account);

    //更新头像
    void updateAvatar(@Param("account") Account account);

}
