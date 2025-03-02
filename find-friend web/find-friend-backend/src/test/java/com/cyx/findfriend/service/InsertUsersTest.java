package com.cyx.findfriend.service;

import com.cyx.findfriend.mapper.UserMapper;
import com.cyx.findfriend.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InsertUsersTest {

    @Resource
    private UserService userService;

    /**
     * 批量用户插入
     */
    @Test
    public void doInsertUsers(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        final int INSERT_NUM=1000;
        List<User> userList=new ArrayList<>();
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
            userList.add(user);
        }
        userService.saveBatch(userList,100);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());

    }

}
