package com.example.demo.config.hbase;

import com.example.demo.config.redis.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class HBaseConfig {
    private static ExecutorService pool = Executors.newFixedThreadPool(20);

    @Autowired
    private HBaseProperties hBaseProperties;

    @Bean
    public org.apache.hadoop.conf.Configuration hBaseConfiguration() {
        System.setProperty("hadoop.home.dir", hBaseProperties.getHadoopHomeDir());
        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", hBaseProperties.getZkQuorum());
        config.set("hbase.zookeeper.property.clientPort", hBaseProperties.getZkClientPort());
        return config;
    }

    @Bean
    public Connection connection(){
        Connection connection=null;
        try {
             connection=ConnectionFactory.createConnection(hBaseConfiguration());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
