package com.example.demo;


import cn.hutool.core.date.DateUtil;
import com.example.demo.common.util.PropertyUtil;
import com.example.demo.config.hbase.HBaseConfig;
import com.example.demo.config.hbase.HbaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@Slf4j
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        log.info("------启动完成------");

    }

}
