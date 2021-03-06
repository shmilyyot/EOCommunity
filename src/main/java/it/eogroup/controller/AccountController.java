package it.eogroup.controller;

import com.github.pagehelper.PageInfo;
import it.eogroup.domain.*;
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
    public ModelAndView toUserFav(@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size) {
        ModelAndView mv = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        try{
            List<favPost> favPosts = accountService.getFavPost(page,size,account.getAccountId());
            PageInfo pageInfo = new PageInfo(favPosts);
            mv.addObject("favPosts",pageInfo);
            logger.info("获取收藏夹成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("获取收藏夹错误");
        }
        return mv;
    }

    //去个人的帖子列表
    @RequestMapping("/userPost")
    public ModelAndView toUserPost(@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size) {
        ModelAndView mv = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        try{
            List<Post> posts = communityService.accountPostFindAll(page,size,account.getAccountId());
            PageInfo pageInfo = new PageInfo(posts);
            mv.addObject("posts",pageInfo);
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

    //打开通知消息页面
    @RequestMapping("/userMessage")
    public ModelAndView showUserMessage(@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size){
        ModelAndView mv = new ModelAndView("account/userMessage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        List<CommentAccountPost> comments = communityService.findUnReadMessagePost(page,size,account.getAccountId());
        PageInfo pageInfo = new PageInfo(comments);
        mv.addObject("comments",pageInfo);
        logger.info("往页面添加未读评论");
        return mv;
    }

    @RequestMapping("/readMessage")
    @ResponseBody
    public String readMessage(Integer commentId){
        try{
            communityService.readMessage(commentId);
            logger.info("成功标记为已读信息");
        }catch (Exception e){
            logger.error("确认已读失败");
            return "false";
        }
        return "true";
    }

    @RequestMapping("/readAllMessage")
    @ResponseBody
    public String readAllMessage(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Account account = accountService.getAccount(authentication.getName());
            communityService.readAllMessage(account.getAccountId());
            logger.info("成功标记所有已读信息");
        }catch (Exception e){
            logger.error("确认已读失败");
            return "false";
        }
        return "true";
    }

    @RequestMapping("/accountBriefInfo")
    @ResponseBody
    public ModelAndView showAccountBriefInfo(Integer commentId){
        ModelAndView mv = new ModelAndView("account/userBriefInfo");
        Integer accountId = communityService.getCommentAccountId(commentId);
        Account account = accountService.getAccountById(accountId);
        mv.addObject("account",account);
        return mv;
    }

}
