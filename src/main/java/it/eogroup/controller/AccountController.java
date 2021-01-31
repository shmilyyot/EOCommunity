package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

//展示层
@Controller
@Repository("accountController")
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    //log4j2日志打印对象（代替System.out.println，前者只能给控制台看，在服务器没有控制台，只能看本地文件）
    private static Logger logger = LogManager.getLogger(AccountController.class);

//    //(管理员)查询所有用户信息
//    @RequestMapping("/findAll")
//    public String findAll(){
//        List<Account> accounts = accountService.findAll();
//        logger.info("展示层：查询所有账户");
//        return "success";
//    }

//    //用户登录
//    @RequestMapping("/login")
//    public String loginAccount(Account account) {
//        logger.info("controller:loginAccount执行了...");
//        Boolean res = accountService.accountLogin(account);
//        if (res) {
//            logger.info("展示层：用户存在，服务器同意登陆");
//            //登录应该添加cookies信息并跳转
//            //待添加......
//            return "success";
//        } else {
//            return "failed";
//        }
//    }
//
    //用户注册
    @RequestMapping("/register")
    public String registerAccount(Account account) {
        Boolean res = accountService.CreateAccount(account);
        if(res){
            logger.info("展示层：注册成功");
            //注册完跳转首页，提示用户重新登陆
            return "redirect:/register.html?Success=true";
        }else{
            logger.info("展示层：用户名已存在，请重新选取用户名");
            //这里前端要清空输入框，允许重新提交
            return "redirect:/register.html?Error=true";
        }
    }
}
