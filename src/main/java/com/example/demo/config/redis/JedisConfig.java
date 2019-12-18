package com.example.demo.config.redis;

import com.example.demo.common.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class JedisConfig {


    @Autowired
    private RedisProperties redisProperties;

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
        String[] addressNode=redisProperties.getAddress().split(",");
        if(addressNode.length==1){
            return null;
        }
        for (String node:addressNode){
            String ip=node.split(":")[0];
            String port=node.split(":")[1];
            jedisClusterNodes.add(new HostAndPort(ip, Integer.valueOf(port)));
        }
        JedisCluster jc = new JedisCluster(jedisClusterNodes,jedisPoolConfig());
        return jc;
    }

    @Bean
    public Jedis jedis(){
        String ip=redisProperties.getAddress().split(":")[0];
        String port=redisProperties.getAddress().split(":")[1];
        JedisPool pool=new JedisPool(jedisPoolConfig(),ip, Integer.valueOf(port));
        Jedis jedis = pool.getResource();
        return jedis;
    }
}
