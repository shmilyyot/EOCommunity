package it.eogroup.controller;

import com.github.pagehelper.PageInfo;
import it.eogroup.domain.Account;
import it.eogroup.domain.Comment;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    //打开首页
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

    //打开论坛
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

    //打开帖子
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

    //提交文本框评论
    @RequestMapping(value="/post/submitComment",method = RequestMethod.POST)
    @ResponseBody
    public String submitComment(Integer postId,String commentText){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Comment comment = new Comment();
        comment.setCommentTime(LocalDateTime.now());
        comment.setCommentText(commentText);
        comment.setPostId(postId);
        comment.setAccountId(accountService.getAccount(authentication.getName()).getAccountId());
        try{
            communityService.insertComment(comment);
            logger.info("发布评论成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("发布评论失败");
            return "false";
        }
        return "true";
    }

    //显示所有社区
    @RequestMapping("/communities")
    public ModelAndView showAllCommunity(){
        ModelAndView mv = new ModelAndView("community/communities");
        mv.addObject("communities",communityService.getAllCommunities());
        logger.info("往页面添加所有社区");
        return mv;
    }

    //回复帖子

    //发帖页面
    @RequestMapping("/post/post/{communityId}")
    public ModelAndView createPost(@PathVariable("communityId")Integer communityId){
        ModelAndView mv = new ModelAndView("community/createPost");
        mv.addObject("community",communityService.getCommunity(communityId));
        logger.info("往发帖页面添加社区信息");
        return mv;
    }

    //发帖子
    @RequestMapping("/post/savePost")
    @ResponseBody
    public String sendPost(@RequestParam(name = "title")String title,@RequestParam(name = "postText")String postText,@RequestParam(name = "communityId")Integer communityId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountService.getAccount(authentication.getName());
        Post post = new Post();
        post.setAccountId(account.getAccountId());
        post.setPostTime(LocalDateTime.now());
        post.setPostTitle(title);
        post.setCommunityId(communityId);
        Comment comment = new Comment();
        try{
            //发帖子
            communityService.insertPost(post);
            //楼主的内容看作第一条评论
            comment.setPostId(communityService.getPostByTime(post.getPostTime()).getPostId());
            comment.setCommentTime(post.getPostTime());
            comment.setAccountId(account.getAccountId());
            comment.setCommentText(postText);
            communityService.insertComment(comment);
            logger.info("成功发布帖子");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("发布帖子失败");
            return "false";
        }
        return "true";
    }

}
