package it.eogroup.service.impl;

import it.eogroup.dao.AccountDao;
import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.security.MessageDigest;

/*
 * 业务层具体实现
 * */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;
    private static Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    //查找所有用户信息
    public List<Account> findAll() {
        logger.info("业务层:查询所有账户");
        return accountDao.findAll();
    }

    @Override
    //用户注册
    public Boolean CreateAccount(Account account) {
        Account resAccount = accountDao.accountExistByName(account.getAccountName());
        if (resAccount == null) {
            logger.info("该用户名可以注册，即将提交注册");
            //密码加密
            account.setAccountPassword(encodingPassword(account.getAccountPassword()));
            logger.info("用户密码已加密");
            accountDao.insertAccount(account);
            return true;
        } else {
            logger.info("账户信息冲突，请重新输入新的账户信息");
            return false;
        }
    }

    @Override
    //用户登录
    public Boolean accountLogin(Account account) {
        Account resAccount = accountDao.accountExistByName(account.getAccountName());
        if (resAccount != null) {
            if (resAccount.getAccountPassword().equals(encodingPassword(account.getAccountPassword()))) {
                logger.info("密码比对成功");
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    //用户密码加密
    public String encodingPassword(String password) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bs = digest.digest(password.getBytes());
            for (byte b : bs) {
                int x = b & 255;
                String s = Integer.toHexString(x);
                if (x < 16) {
                    sb.append("0");
                }
                sb.append(s);
            }
        } catch (Exception e) {
            logger.info("密码加密失败");
        }
        return sb.toString();
    }


}
