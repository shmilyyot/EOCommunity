package it.eogroup.service.impl;

import it.eogroup.dao.AccountDao;
import it.eogroup.domain.Account;
import it.eogroup.domain.Role;
import it.eogroup.service.AccountService;
import it.eogroup.service.AvatarPath;
import it.eogroup.service.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
* 业务层具体实现
* */
@Service("accountService")
@Transactional //声明式事务
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;
    @Resource
    private DateConverter dateConverter;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Override
    //查找所有用户信息
    public List<Account> findAll(){
        logger.info("业务层:查询所有账户");
        return accountDao.findAll();
    }

    @Override
    //用户注册
    public Boolean CreateAccount(HttpServletRequest request,Account account) {
        String username = account.getAccountName();
        Account resAccount =  accountDao.accountExistByName(username);
        if(resAccount == null){
            logger.info("该用户名可以注册，即将提交注册");
            account.setAccountPassword(bCryptPasswordEncoder.encode(account.getAccountPassword()));
            account.setAccountRegisterDate(LocalDate.now());
            String path = "/images/defaultAvatar.png";
            account.setAccountAvatar(path);
            accountDao.insertAccount(account);
            accountDao.insertRole(accountDao.findAccountId(account.getAccountName()));
            logger.info("用户角色权限已确认");
            return true;
        }else{
            logger.info("该用户名已存在");
            return false;
        }
    }

    @Override
    //根据账户名获得账户
    public Account getAccount(String username) {
        return accountDao.accountExistByName(username);
    }

    @Override
    //查找用户角色
    public Role getRole(String username) {
        Integer id = accountDao.findAccountId(username);
        Role role = accountDao.roleExistById(id);
        return role;
    }

    @Override
    //更新用户信息
    public void updateAccountProfile(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    //更新密码
    public ModelAndView updateAccountPassword(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String password = request.getParameter("oldPassword");
        String newpassword = request.getParameter("accountPassword");
        Account account = accountDao.accountExistByName(username);
        String password2 = account.getAccountPassword();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean matches = bc.matches(password,password2);
        if(matches){
            logger.info("密码更新成功");
            account.setAccountPassword(bCryptPasswordEncoder.encode(newpassword));
            accountDao.updatePassword(account);
            return new ModelAndView("account/userPassword","status","success");
        }else{
            logger.info("旧密码不正确");
            return new ModelAndView("account/userPassword","status","failed");
        }
    }

    @Override
    //更新头像
    public void updateAvatar(HttpServletRequest request,MultipartFile accountFace) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountDao.accountExistByName(authentication.getName());
        String path = request.getSession().getServletContext().getRealPath(AvatarPath.IMG_PATH);
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String oldAvatar = account.getAccountAvatar();
        File delfile = new File(oldAvatar);
        if(delfile.exists()) delfile.delete();
        String uuid = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        String filename = accountFace.getOriginalFilename();
        filename = uuid+"_"+filename;
        String savepath = AvatarPath.IMG_PATH+filename;
        String filepath = path+filename;
        try{
            accountFace.transferTo(new File(filepath));
            account.setAccountAvatar(savepath);
        }catch (Exception e){
            e.printStackTrace();
        }
        accountDao.updateAvatar(account);
    }

    @Override
    public Map<String, String> getAccountInfo() {
        Map<String,String> map = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountDao.accountExistByName(authentication.getName());
        Role role = getRole(account.getAccountName());
        map.put("accountName",account.getAccountName());
        map.put("accountEmail", account.getAccountEmail());
        map.put("accountBirthday", dateConverter.convertString(account.getAccountBirthday()));
        map.put("registerDate",dateConverter.convertString(account.getAccountRegisterDate()));
        map.put("accountAddress",account.getAccountAddress());
        map.put("roleName", role.getRoleName());
        map.put("accountAvatar",account.getAccountAvatar());
        return map;
    }

    @Override
    public Map<String, String> getAccountAvatar() {
        Map<String,String> map = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = accountDao.accountExistByName(authentication.getName());
        map.put("accountAvatar",account.getAccountAvatar());
        return map;
    }

}
