package it.eogroup.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

//用户密码加密
public class BCryptPasswordEncoderUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        System.out.println("密码加密成功");
        return bCryptPasswordEncoder.encode(password);
    }
}
