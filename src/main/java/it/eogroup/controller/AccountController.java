package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.domain.favPost;
import it.eogroup.service.AccountService;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*账户修改控制器*/
@Controller
@Repository("accountController")
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;
    @Resource
    private CommunityService communityService;
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    //去收藏夹
    @RequestMapping("/userFav")
    public ModelAndView toUserFav() {
        ModelAndView mv = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        try{
            List<favPost> favPosts = accountService.getFavPost(account.getAccountId());
            for(favPost favpost:favPosts) System.out.println(favpost);
            mv.addObject("favPosts",favPosts);
            logger.info("获取收藏夹成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取收藏夹错误");
        }
        return mv;
    }

    //去个人的帖子列表
    @RequestMapping("/userPost")
    public ModelAndView toUserPost() {
        ModelAndView mv = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        try{
            mv.addObject("posts",communityService.accountPostFindAll(account.getAccountId()));
            logger.info("往个人资料添加帖子");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查找帖子失败");
        }
        return mv;
    }

    //去用户密码页
    @RequestMapping("/userPassword")
    public ModelAndView toUserPassword() {
        return new ModelAndView();
    }

    //去用户头像页
    @RequestMapping("/userFace")
    public ModelAndView toUserAvatar() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> map = accountService.getAccountAvatar();
        modelAndView.addAllObjects(map);
        logger.info("修改头像成功");
        return modelAndView;
    }

    //采用用户信息并且返回个人资料以修改
    @RequestMapping("/userSet")
    public ModelAndView toAccountSet() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        modelAndView.addObject(account);
        logger.info("请求用户信息已返回");
        return modelAndView;
    }

    //前端ajax判断用户名是否存在
    @RequestMapping("/findByName")
    @ResponseBody
    public String checkAccountName(String username){
        if(accountService.getAccount(username) != null){
            logger.info("展示层：用户名已存在，请重新选取用户名");
            return "false";
        }
        return "true";
    }

    //返回用户信息
    @RequestMapping("/userInfo")
    public ModelAndView toAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String,String> map = accountService.getAccountInfo();
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
        return accountService.updateAccountPassword(request);
    }

    //修改头像
    @RequestMapping("/uploadAvatar")
    public ModelAndView toSaveFace(HttpServletRequest request,@RequestParam(value="accountFace",required=false) MultipartFile accountFace){
        Map<String,String> map = accountService.updateAvatar(request,accountFace);
        ModelAndView modelAndView = new ModelAndView("account/userFace");
        logger.info("上传头像");
        modelAndView.addAllObjects(map);
        return modelAndView;
    }
}
