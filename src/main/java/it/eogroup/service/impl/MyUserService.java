package it.eogroup.service.impl;

import it.eogroup.dao.AccountDao;
import it.eogroup.domain.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyUserService implements UserDetailsService {
    @Resource
    private AccountDao accountDao;

    @Override
    //用户登录，只有这个需要在service层实现，其他操作都在controller操作
    //查找account，进一步封装成UserDetails提交给spring-security使用
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        Account resAccount = null;
        try{
            resAccount =  accountDao.accountExistByName(accountName);
            if(resAccount == null) return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        User user = null;
        if (resAccount != null) {
            user = new User(resAccount.getAccountName(),resAccount.getAccountPassword(),getAuthority(accountDao.findAccountRole(accountName)));
        }
        System.out.println("封装成功");
        return user;
    }
    //返回用户权限集合
    public List<SimpleGrantedAuthority> getAuthority(String role){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_"+role));
        return list;
    }
}
