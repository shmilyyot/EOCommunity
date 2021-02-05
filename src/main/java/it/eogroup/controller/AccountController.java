package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import it.eogroup.service.AccountService;
import it.eogroup.service.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    //采用用户信息并且返回个人资料页显示
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

    //前端ajax判断用户名是否存在
    @RequestMapping("/findByName")
    @ResponseBody
    public String checkAccountName(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(accountService.getAccount(username) != null){
            logger.info("展示层：用户名已存在，请重新选取用户名");
            return "false";
        }
        return "true";
    }

    //去收藏夹
    @RequestMapping("/userFav")
    public ModelAndView toUserFav() {
        return new ModelAndView();
    }

    //去个人的帖子列表
    @RequestMapping("/userPost")
    public ModelAndView toUserPost() {
        return new ModelAndView();
    }

    //去用户密码页
    @RequestMapping("/userPassword")
    public ModelAndView toUserPassword() {
        return new ModelAndView();
    }

    //打开用户设置页并返回用户信息可供修改
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

    //保存修改的资料
    @RequestMapping("/saveProfile")
    public ModelAndView profileUpdate(Account account){
        accountService.updateAccountProfile(account);
        logger.info("账户信息更新成功");
        return new ModelAndView("account/userSet","status","success");
    }

    //修改密码
    @RequestMapping("/savePassword")
    public ModelAndView toSavePassword(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String password = request.getParameter("oldPassword");
        String newpassword = request.getParameter("accountPassword");
        Account account = accountService.getAccount(username);
        String password2 = account.getAccountPassword();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean matches = bc.matches(password,password2);
        if(matches){
            logger.info("密码更新成功");
            account.setAccountPassword(bCryptPasswordEncoder.encode(newpassword));
            System.out.println(account);
            accountService.updateAccountPassword(account);
            return new ModelAndView("account/userPassword","status","success");
        }else{
            logger.info("旧密码不正确");
            return new ModelAndView("account/userPassword","status","failed");
        }
    }

    //修改头像
    @RequestMapping("/account/saveFace")
    public ModelAndView toSaveFace(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
