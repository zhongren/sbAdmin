package com.example.demo;


import com.example.demo.common.util.PropertyUtil;
import com.example.demo.config.property.PropertyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class StartApplication {


    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("------启动完成------");
        //System.out.println(PropertyConfig.properties);
        System.out.println(PropertyUtil.getProperty("ab","qqq"));
    }

}
