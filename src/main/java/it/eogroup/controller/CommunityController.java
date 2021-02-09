package it.eogroup.controller;

import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;

/*板块帖子控制器*/
@Controller
@RequestMapping("/community")
@Repository("communityController")
public class CommunityController {

    @Resource
    private CommunityService communityService;
    private static final Logger logger = LogManager.getLogger(CommunityController.class);

    @RequestMapping("/")
    public String index(Model model){
        logger.info("跳转了首页");
        model.addAttribute("communities",communityService.getTopCommunity());
        model.addAttribute("posts",communityService.getTopPosts());
        logger.info("往首页添加了热门社区和热门帖子");
        return "index";
    }

    @RequestMapping("/findPosts")
    public ModelAndView findPosts(@RequestParam(name="page",defaultValue = "1")Integer page,@RequestParam(name = "size",defaultValue = "10")int size){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("Posts",communityService.getTopPosts());
        logger.info("往页面添加了帖子");
        return mv;
    }

    @RequestMapping("/community/{communityId}")
    public ModelAndView toCommunity(@PathVariable("communityId")Integer communityId ){
        ModelAndView mv = new ModelAndView("/community/community");
        mv.addObject("community",communityService.getCommunity(communityId));
        mv.addObject("post",communityService.getTopPosts());
        logger.info("往页面添加社区信息");
        return mv;
    }

}
