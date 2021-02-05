package it.eogroup.service.impl;

import it.eogroup.dao.AccountDao;
import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/*
* 业务层具体实现
* */
@Service("accountService")
@Transactional //声明式事务
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    //查找所有用户信息
    public List<Account> findAll(){
        logger.info("业务层:查询所有账户");
        return accountDao.findAll();
    }

    @Override
    //用户注册
    public Boolean CreateAccount(Account account) {
        String username = account.getAccountName();
        Account resAccount =  accountDao.accountExistByName(username);
        if(resAccount == null){
            logger.info("该用户名可以注册，即将提交注册");
            account.setAccountPassword(bCryptPasswordEncoder.encode(account.getAccountPassword()));
            account.setAccountRegisterDate(LocalDate.now());
            accountDao.insertAccount(account);
            accountDao.insertRole(accountDao.findAccountId(account.getAccountName()));
            logger.info("用户角色权限已确认");
            return true;
        }else{
            logger.info("该用户名已存在");
            return false;
        }
    }

    @Override
    //根据账户名获得账户
    public Account getAccount(String username) {
        return accountDao.accountExistByName(username);
    }

    @Override
    //查找用户角色
    public Role getRole(String username) {
        Integer id = accountDao.findAccountId(username);
        Role role = accountDao.roleExistById(id);
        return role;
    }

    @Override
    //更新用户信息
    public void updateAccountProfile(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    //更新密码
    public void updateAccountPassword(Account account) {
        accountDao.updatePassword(account);
    }

}
