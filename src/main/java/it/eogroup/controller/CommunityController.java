package it.eogroup.controller;

import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("invatations",communityService.getTopInvatations());
        logger.info("往首页添加了社区和帖子");
        return "index";
    }

}
