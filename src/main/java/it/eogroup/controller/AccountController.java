package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import it.eogroup.service.AccountService;
import it.eogroup.service.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/*账户修改控制器*/
@Controller
@Repository("accountController")
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private DateConverter dateConverter;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    //采用用户信息
    @RequestMapping("/userSet")
    public ModelAndView toAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            logger.info("toUserSet()方法查询不到认证用户");
            modelAndView.setViewName("404");
            return modelAndView;
        }
        Account account = accountService.getAccount(authentication.getName());
        modelAndView.addObject(account);
        logger.info("请求用户信息已返回");
        return modelAndView;
    }

//    @RequestMapping("/findByName")
//    @ResponseBody
//    public Map<String,String> checkAccountName(HttpServletRequest request){
//        Map<String,String> map = new HashMap<>();
//        if(accountService.getAccount(request.getParameter("username")) != null){
//            logger.info("展示层：用户名已存在，请重新选取用户名");
//            map.put("username","false");
//        }
//        logger.info("执行了");
//        return map;
//    }

    @RequestMapping("/findByName")
    @ResponseBody
    public String checkAccountName(String username){
        if(accountService.getAccount(username) != null){
            logger.info("展示层：用户名已存在，请重新选取用户名");
            return "false";
        }
        return "true";
    }

    @RequestMapping("/userFav")
    public ModelAndView toUserFav() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping("/userPost")
    public ModelAndView toUserPost() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping("/userInfo")
    public ModelAndView toUserSet() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,String> map = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            logger.info("toUserSet()方法查询不到认证用户");
            modelAndView.setViewName("404");
            return modelAndView;
        }
        Account account = accountService.getAccount(authentication.getName());
        Role role = accountService.getRole(account.getAccountName());
        map.put("accountName",account.getAccountName());
        map.put("accountEmail", account.getAccountEmail());
        map.put("accountBirthday", dateConverter.convertString(account.getAccountBirthday()));
        map.put("registerDate",dateConverter.convertString(account.getAccountRegisterDate()));
        map.put("accountAddress",account.getAccountAddress());
        map.put("roleName", role.getRoleName());
        modelAndView.addAllObjects(map);
        logger.info("请求用户信息已返回");
        return modelAndView;
    }

}
