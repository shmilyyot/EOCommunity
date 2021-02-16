package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.domain.CommentAccount;
import it.eogroup.service.AccountService;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*登陆注册控制器*/
@Controller
@Repository("pagesController")
@RequestMapping("/pages")
public class PagesContorller {

    @Resource
    private AccountService accountService;
    @Resource
    private CommunityService communityService;
    private static final Logger logger = LogManager.getLogger(PagesContorller.class);

    //检测Thymeleaf是否可用
    @RequestMapping("/header")
    public ModelAndView toHeader() {
        ModelAndView mv = new ModelAndView("header");
//        if(SecurityContextHolder.getContext().getAuthentication() != null){
//            Account account = accountService.getAccount(SecurityContextHolder.getContext().getAuthentication().getName());
//            List<CommentAccount> comments = communityService.findAllMessage(1,10,account.getAccountId());
//            mv.addObject("messageNum",comments.size());
//        }
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Account account = accountService.getAccount(authentication.getName());
            List<CommentAccount> comments = communityService.findAllMessage(account.getAccountId());
            mv.addObject("messageNum",comments.size());
        }catch (Exception e){
            logger.error("未登录获取不到未读消息，以异常代替");
        }
        logger.info("跳转header");
        return mv;
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

    //注册账户并跳转登陆页面
    @RequestMapping("/registerAccount")
    public String registerAccount(HttpServletRequest request,Account account, HttpServletResponse response){
        Boolean res = accountService.CreateAccount(request,account);
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
