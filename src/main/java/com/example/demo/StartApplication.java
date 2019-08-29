package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.Subject;

@SpringBootApplication
@Slf4j
public class StartApplication {


    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("------启动完成------");
    }

}
