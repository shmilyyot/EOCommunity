package it.eogroup.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Date;

/*
*
* 通常是JavaBean，与数据库表对应
* */
@Getter
@Setter
@ToString
public class Account {
    private int accountId;
    private String accountName;
    private String accountPassword;
    private Date accountBirthday;
    private String accountEmail;
    private String accountAddress;
}
