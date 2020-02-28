package com.example.demo;

import com.example.demo.common.cache.RedisCache;
import com.example.demo.config.hbase.HbaseUtils;
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
    public void redisCluster() {
        redisCache.put("aa", 111, 1000L);
    }

    @Test
    public void redis() {
        redisCache.put("aa", 111, -1L);
    }


    @Test
    public void hbase() {
        HbaseUtils.deleteTable("t1");
        HbaseUtils.createTable("t1", new String[]{"cf1"});

        for (int i = 1; i <= 10; i++) {
            String rowKey = "A_";
            rowKey = rowKey + i;
            System.out.println(rowKey);
            HbaseUtils.putRow("t1", rowKey, "cf1", "address", i + "");
        }

        for (int i = 1; i <= 10; i++) {
            String str = HbaseUtils.getValue("t1", "A_" + i, "cf1", "address");

            System.out.println("结果" + str);
        }
    }
}
