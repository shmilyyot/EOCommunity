package it.eogroup.domain;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class test {
    @Resource
    private DruidDataSource dataSource;
    //测试数据源能否自动注入
    @Test
    public void insert() throws SQLException {
        String name = "Tom";
        String password = "123456";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(username,password) values(?,?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.execute();
        connection.close();
    }
}