package com.example.demo.config.redis;

import com.example.demo.common.util.PropertyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisConfig {
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(800);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(100);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(5);

        jedisPoolConfig.setMaxWaitMillis(3000);

        return jedisPoolConfig;
    }
    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
       //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
        JedisCluster jc = new JedisCluster(jedisClusterNodes,jedisPoolConfig());
        return jc;
    }


}
