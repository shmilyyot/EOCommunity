package it.eogroup.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

//测试log4j2的日志输出
public class testlog4j {
    @Test
    public void test(){
        Logger logger = LogManager.getLogger(testlog4j.class);
        logger.info("打印了xxx");
    }
}
