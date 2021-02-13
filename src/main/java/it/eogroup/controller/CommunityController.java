package it.eogroup.controller;

import com.github.pagehelper.PageInfo;
import it.eogroup.domain.CommentAccount;
import it.eogroup.domain.Post;
import it.eogroup.service.AccountService;
import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/*板块帖子控制器*/
@Controller
@RequestMapping("/community")
@Repository("communityController")
public class CommunityController {
    @Resource
    private AccountService accountService;
    @Resource
    private CommunityService communityService;
    private static final Logger logger = LogManager.getLogger(CommunityController.class);

    @RequestMapping("/")
    public String index(Model model){
        logger.info("跳转了首页");
        model.addAttribute("communities",communityService.getTopCommunity());
        model.addAttribute("posts",communityService.getTopPosts());
        List<Post> posts = communityService.getTopPosts();
        for(Post post:posts){
            model.addAttribute(""+post.getCommunityId(),communityService.getCommunityName(post.getCommunityId()));
        }
        logger.info("往首页添加了热门社区和热门帖子");
        return "index";
    }

    @RequestMapping("/community/{communityId}")
    public ModelAndView toCommunity(@PathVariable("communityId")Integer communityId,@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size ){
        ModelAndView mv = new ModelAndView("community/community");
        mv.addObject("community",communityService.getCommunity(communityId));
        logger.info("往页面添加社区信息");
        List<Post> posts = communityService.postFindAll(page,size,communityId);
        PageInfo pageInfo = new PageInfo(posts);
        mv.addObject("pageinfo",pageInfo);
        for(Post post:posts){
            mv.addObject(""+post.getPostId(),communityService.getPostName(post.getPostId()));
        }
        logger.info("往页面添加了帖子");
        return mv;
    }

    @RequestMapping("/post/{postId}")
    public ModelAndView toPost(@PathVariable("postId")Integer postId,@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")Integer size,@RequestParam(name = "communityId",defaultValue = "1")Integer communityId){
        ModelAndView mv = new ModelAndView("community/postContent");
        //加入社区信息
        mv.addObject("community",communityService.getCommunity(communityId));
        mv.addObject("post",communityService.getPost(postId));
        logger.info("往页面添加社区信息");
        //加入分页评论信息
        List<CommentAccount> comments = communityService.commentAccountFindAll(page,size,postId);
        PageInfo pageInfo = new PageInfo(comments);
        mv.addObject("pageinfo",pageInfo);
        logger.info("往帖子添加了评论");
        return mv;
    }

    //收藏帖子
    @RequestMapping("/post/fav")
    @ResponseBody
    public String addToFav(String url,String title){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer accountId = accountService.getAccount(authentication.getName()).getAccountId();
        try{
            if(!communityService.insertFavPost(accountId,url,title)){
                logger.error("帖子已存在");
                return "exist";
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("添加收藏夹失败");
            return "false";
        }
        return "true";
    }

    //取消收藏
    @RequestMapping("/post/unFav")
    @ResponseBody
    public String unFav(Integer favId){
        try{
            communityService.deleteFavPost(favId);
        }catch (Exception e){
            logger.error("取消收藏失败");
            return "false";
        }
        return "true";
    }

    //上传图片
    @RequestMapping("/commumity/upload/editor/img")
    public ModelAndView uploadEditorImg(){
        return new ModelAndView();
    }

    //提交文本框评论
    @RequestMapping("/post/submitComment")
    public ModelAndView submitComment(){
        LocalDateTime commentTime = LocalDateTime.now();
        return new ModelAndView();
    }

    //显示所有社区
    @RequestMapping("/community/Communities")
    public ModelAndView showAllCommunity(){
        return new ModelAndView();
    }

    //回复帖子

}
