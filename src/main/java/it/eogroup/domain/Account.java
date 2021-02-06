package it.eogroup.domain;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/*
*
* 通常是JavaBean，与数据库表对应
* 封装数据禁止使用基本类型，必须使用包装类（包装类允许为null，而基本类型不行，会报异常）
* */
@Data
public class Account {
    private Integer accountId;
    private String accountName;
    private String accountPassword;
    private LocalDate accountBirthday;
    private LocalDate accountRegisterDate;
    private String accountEmail;
    private String accountAddress;
    private String accountAvatar;
}
