package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

//展示层
@Controller
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountService accountService;
    //log4j2日志打印对象（代替System.out.println，前者只能给控制台看，在服务器没有控制台，只能看本地文件）
    private static Logger logger = LogManager.getLogger(AccountController.class);

    @RequestMapping("/findAll")
    public String findAll(){
        List<Account> list = accountService.findAll();
        System.out.println("展示层：查询所有账户");
        logger.info("展示层：查询所有账户");
        return "success";
    }
}
