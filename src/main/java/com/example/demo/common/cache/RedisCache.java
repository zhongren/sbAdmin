package com.example.demo.common.cache;

import com.example.demo.common.util.AppCtx;
import com.example.demo.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Set;


@Slf4j
public class RedisCache implements Cache {

    /*
    @Autowired
    private JedisCluster jedis;

    @Autowired
    private Jedis jedis;
    */
    private Jedis getJedis() {
        return AppCtx.getBean(Jedis.class);
    }

    protected String serialize(Object data) {
        if (data == null) return null;
        return JsonUtil.objectToJson(data);
    }

    protected <T> T deserialize(String data, Class<T> clazz) {
        try {
            return JsonUtil.jsonToBean(data, clazz);
        } catch (Exception e) {
            log.error("Redis反序列化失败", e);
            return null;
        }
    }


    @Override
    public <T> T put(String key, T value, Long expire) {
        if (StringUtils.isEmpty(key) || value == null) {
            return null;
        }
        if (expire == null || expire <= 0) {
            getJedis().set(key, serialize(value));
            return value;
        }
        getJedis().psetex(key, expire, serialize(value));
        return value;
    }

    @Override
    public boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        boolean exists = getJedis().exists(key);
        return exists;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        T t = deserialize(getJedis().get(key), clazz);
        return t;
    }

    @Override
    public void del(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        getJedis().del(key);

    }

    @Override
    public void del(String[] key) {
        if (null == key || key.length <= 0) {
            return;
        }
        getJedis().del(key);
    }

    @Override
    public <T> T recache(String key, Class<T> clazz, Long expire) {

        T value = null;
        if ((value = get(key, clazz)) == null) {
            return null;
        }
        getJedis().expireAt(key, expire);
        return value;
    }

    @Override
    public Set<String> keys(String pattern) {
        Set<String> keys = null;
        try {
            keys = getJedis().keys(pattern);
        } catch (Exception e) {
            log.error("Redis获取keys异常", e);
        }
        return keys;
    }
}
