package it.eogroup.controller;

import it.eogroup.domain.Account;
import it.eogroup.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

//展示层
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(){
        System.out.println("展示层：查询所有账户");
        List<Account> list = accountService.findAll();
        return "success";
    }
}
