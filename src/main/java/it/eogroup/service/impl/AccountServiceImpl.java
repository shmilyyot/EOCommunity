package it.eogroup.service.impl;

import it.eogroup.dao.AccountDao;
import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
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
    private static Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    //查找所有用户信息
    public List<Account> findAll(){
        logger.info("业务层:查询所有账户");
        return accountDao.findAll();
    }

    @Override
    //用户注册
    public Boolean CreateAccount(Account account) {
        Account resAccount =  accountDao.accountExistByName(account.getAccountName());
        if(resAccount == null){
            logger.info("该用户名可以注册，即将提交注册");
            //密码加密
            account.setAccountPassword(bCryptPasswordEncoder.encode(account.getAccountPassword()));
            accountDao.insertAccount(account);
            return true;
        }else{
            logger.info("该用户名已存在");
            return false;
        }
    }


//    @Override
//    //用户登录
//    public Boolean accountLogin(Account account) {
//        logger.info("accountLogin执行了");
//        Account resAccount = null;
//        try{
//            resAccount =  accountDao.accountExistByName(account.getAccountName());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        if(resAccount != null){
//            if(resAccount.getAccountPassword().equals(encodingPassword(account.getAccountPassword()))){
//                logger.info("密码比对成功");
//                return true;
//            }else return false;
//        }
//        logger.info("无效的用户名或密码");
//        return false;
//    }
//
//    @Override
//    //用户密码加密
//    //spring-security自带，无需手写，废弃
//    public String encodingPassword(String password) {
//        StringBuffer sb = new StringBuffer();
//        try{
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            byte[] bs = digest.digest(password.getBytes());
//            for (byte b:bs){
//                int x = b & 255;
//                String s = Integer.toHexString(x);
//                if(x < 16){
//                    sb.append("0");
//                }
//                sb.append(s);
//            }
//        }catch (Exception e){
//            logger.info("密码加密失败");
//        }
//        logger.info("encodingPassword密码加密执行了");
//        return sb.toString();
//    }


    @Override
    //用户登录，只有这个需要在service层实现，其他操作都在controller操作
    //查找account，进一步封装成UserDetails提交给spring-security使用
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account resAccount = null;
        try{
            resAccount =  accountDao.accountExistByName(accountName);
            if(resAccount != null){
                logger.info("查询到账户:"+ resAccount.getAccountName());
            }else{
                logger.info("查询不到账户："+resAccount.getAccountName());
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        User user = new User(resAccount.getAccountName(),resAccount.getAccountPassword(),getAuthority());
        logger.info("封装账户给Spring Security");
        System.out.println(user);
        return user;
    }

    //返回用户权限集合
    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }
}
