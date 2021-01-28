package it.eogroup.service.impl;

import it.eogroup.dao.UserDao;
import it.eogroup.domain.User;
import it.eogroup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author glx
 * @date 2021/01/27/15:33
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Boolean isLogin(User user) {
        User U = userDao.isLogin(user);
        return U != null;
    }

    @Override
    public Boolean isRegister(User user) {
        Boolean b = userDao.isRegister(user);
        return b;
    }
}
