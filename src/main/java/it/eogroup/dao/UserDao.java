package it.eogroup.dao;

import it.eogroup.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author glx
 * @date 2021/01/27/15:30
 */
@Repository
public interface UserDao {
    User isLogin(User user);
    Boolean isRegister(User user);
}
