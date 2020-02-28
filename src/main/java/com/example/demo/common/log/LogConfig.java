package com.example.demo.common.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
public class LogConfig {
    /*
    public LogConfig(){
        System.out.println("logConfig");
    }
*/
    public void autoLog() {
        System.out.println("autoLog");
    }
}
