package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/*登陆注册控制器*/
@Controller
@Repository("pagesController")
@RequestMapping("/pages")
public class PagesContorller {

    @Resource
    private AccountService accountService;
    private static final Logger logger = LogManager.getLogger(PagesContorller.class);

    //检测Thymeleaf是否可用
    @RequestMapping("/header")
    public ModelAndView toHeader() {
        ModelAndView mv = new ModelAndView("header");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//        if(!SecurityContextHolder.getContext().getAuthentication().getAuthorities().equals("ROLE_ANONYMOUS")){
//            Map<String,String> avatar= accountService.getAccountAvatar();
//            mv.addAllObjects(avatar);
//            logger.info("已登录添加头像");
//        }
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
