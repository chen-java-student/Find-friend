package com.cyx.findfriend.service;
import java.util.Date;

import com.cyx.findfriend.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test(){
        //增
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("cyxString","cat");
        valueOperations.set("cyxInt",1);
        valueOperations.set("cyxDouble",1.0);
        User user=new User();
        user.setId(1L);
        user.setUsername("cyxTest");
        valueOperations.set("cyxUser",user);

        //查
        Object cyx = valueOperations.get("cyxString");
        Assertions.assertTrue("cat".equals((String)cyx));
        cyx=valueOperations.get("cyxInt");
        Assertions.assertTrue(1 == (Integer) cyx);
        cyx=valueOperations.get("cyxDouble");
        Assertions.assertTrue(1.0 == (Double) cyx);

        System.out.println(valueOperations.get("cyxUser"));


    }
}
