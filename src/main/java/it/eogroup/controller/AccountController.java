package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;


//展示层
@Controller
@Repository("accountController")
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    //采用用户信息
    @RequestMapping("userInfo")
    public ModelAndView toAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            logger.info("toAccountInfo()方法查询不到认证用户");
            modelAndView.setViewName("404");
            return modelAndView;
        }
        String username = authentication.getName();
        Account account = accountService.getAccount(username);
        modelAndView.addObject("account",account);
        logger.info("请求用户信息已返回");
        return modelAndView;
    }

}
