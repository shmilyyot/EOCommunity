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
@Repository
@Getter
@Setter
@ToString
public class Account {
    private String name;
    private String password;
    private Date birthday;
    private String email;
    private String phoneNumber;
    private String address;
}
