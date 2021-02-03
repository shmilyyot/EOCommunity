package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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

    //用户注册
    @RequestMapping("/CreateAccount")
    public String registerAccount(Model model, Account account) {
        Boolean res = accountService.CreateAccount(account);
        if(res){
            logger.info("展示层：注册成功");
            model.addAttribute("status",true);
        }else{
            logger.info("展示层：用户名已存在，请重新选取用户名");
            model.addAttribute("status",false);
        }
        return "register";
    }

}
