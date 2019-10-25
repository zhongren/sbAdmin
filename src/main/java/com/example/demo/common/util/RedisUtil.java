package com.example.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


public class RedisUtil {
    private RedisTemplate<String, String> redisTemplate = AppCtx.getBean(RedisTemplate.class);

    public <T> T put(String key, T value, Long expire) {
        if (StringUtils.isEmpty(key) || value == null) {
            return null;
        }
        if (expire == null || expire <= 0) {
            redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value));
            return value;
        }
        redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value), expire);
        return value;
    }

    public Boolean exists(String key) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        return redisTemplate.hasKey(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        T t = JsonUtil.jsonToBean(redisTemplate.opsForValue().get(key), clazz);
        return t;
    }

    public <T> List getList(String key, Class<T> clazz) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        List list = JsonUtil.jsonToList(redisTemplate.opsForValue().get(key), clazz);
        return list;
    }

    public void del(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(key);
    }

    public void del(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);
    }
}
