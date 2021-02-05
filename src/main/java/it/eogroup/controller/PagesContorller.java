package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*页面跳转控制器*/
@Controller
@Repository("pagesController")
@RequestMapping("/pages")
public class PagesContorller {

    @Resource
    private AccountService accountService;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    //检测Thymeleaf是否可用
    @RequestMapping("/header")
    public String toHeader() {
        logger.info("跳转header");
        return "header";
    }

    @RequestMapping("/login")
    public String toLogin() {
        logger.info("跳转login");
        return "login";
    }

    @RequestMapping("/register")
    public String toRegister() {
        logger.info("跳转register");
        return "register";
    }

//    @RequestMapping("/registerAccount")
//    public String registerAccount(Account account) {
//        Boolean res = accountService.CreateAccount(account);
//        if(res){
//            logger.info("展示层：注册成功");
//        }else{
//            logger.info("账号注册失败");
//        }
//        return "redirect:login.html";
//    }

    //注册账户并顺便判断用户名是否冲突
    @RequestMapping("/registerAccount")
    public String registerAccount(Account account,HttpServletResponse response){
        Boolean res = accountService.CreateAccount(account);
        response.setContentType("text/html;charset=UTF-8");
        if(res){
            logger.info("注册成功");
            try{
                //不生效，不知道为什么，先不改
                PrintWriter out = response.getWriter();
                out.print("<script type='text/javascript'>alert('注册成功');</script>");
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            logger.info("发生意外错误，注册失败");
            return "redirect:register.html";
        }
        return "redirect:login.html";
    }

}
