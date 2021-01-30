package it.eogroup.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//用户密码加密
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
}
