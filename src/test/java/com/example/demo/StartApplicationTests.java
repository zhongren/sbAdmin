package com.example.demo;

import com.example.demo.common.cache.RedisCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartApplicationTests {

    @Autowired
    RedisCache redisCache;
    @Test
    public void userCreate() {
       redisCache.put("aa",111,1000L);
    }

}
