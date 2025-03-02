package com.cyx.findfriend;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author cyx
 */
@SpringBootApplication
@MapperScan("com.cyx.findfriend.mapper")
@EnableScheduling
public class FindFriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindFriendApplication.class, args);
    }

}

