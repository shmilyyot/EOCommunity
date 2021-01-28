package it.eogroup.service;

import it.eogroup.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author glx
 * @date 2021/01/27/15:32
 */

public interface UserService {
    Boolean isLogin(User user);
    Boolean isRegister(User user);
}
