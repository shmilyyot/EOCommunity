package it.eogroup.service;

import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import it.eogroup.domain.favPost;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*
* 账户业务层
* Servlet和Dao之间的缓冲层，解耦，是Dao层变化不会影响上层服务层
* */
public interface AccountService {

    //查询所有账户信息
    List<Account> findAll();

    //用户注册
    Boolean CreateAccount(HttpServletRequest request,Account account);

    //获得用户
    Account getAccount(String username);

    Account getAccountById(Integer accountId);

    //获得用户角色
    Role getRole(String username);

    //更新用户信息
    void updateAccountProfile(Account account);

    //更新密码
    ModelAndView updateAccountPassword(HttpServletRequest request);

    //上传头像
    Map<String,String> updateAvatar(HttpServletRequest request, MultipartFile accountFace);

    //获得用户信息
    Map<String,String> getAccountInfo();

    //获得用户头像路径
    Map<String, String> getAccountAvatar();

    String getAccountName(Integer accountId);

    List<favPost> getFavPost(Integer page, Integer size,Integer accountId);

}
