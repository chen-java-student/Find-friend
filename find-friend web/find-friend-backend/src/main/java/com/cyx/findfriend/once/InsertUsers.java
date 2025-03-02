package com.cyx.findfriend.once;

import com.cyx.findfriend.mapper.UserMapper;
import com.cyx.findfriend.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@Component
public class InsertUsers {

    @Resource
    private UserMapper userMapper;

    //@Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUsers(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
       final int INSERT_NUM=1000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user=new User();
            user.setUsername("假陈毓轩");
            user.setUserAccount("chenyuxuan");
            user.setAvatarUrl("https://c-ssl.dtstatic.com/uploads/blog/202207/12/20220712023514_fb2df.thumb.1000_0.jpeg");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("123");
            user.setEmail("123@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setTags("");
            user.setPlanetCode("1111");
            user.setProfile("一条咸鱼");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

    }

}
