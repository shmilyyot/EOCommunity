package it.eogroup.controller;


import it.eogroup.service.CommunityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET,value ="/")
    public ModelAndView index(){
        logger.info("跳转了首页");
        ModelAndView mv = new ModelAndView("redirect:../../index.html");
        mv.addObject("community",communityService.getTopCommunity());
        mv.addObject("invatation",communityService.getTopInvatations());
        return mv;
    }

}
