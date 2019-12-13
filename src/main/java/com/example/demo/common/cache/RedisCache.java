package com.example.demo.common.cache;

import com.example.demo.common.util.AppCtx;
import com.example.demo.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

@Component
@Slf4j
public class RedisCache implements Cache {

    @Autowired
    private JedisCluster jedisCluster;

    protected String serialize( Object data ){
        if( data == null ) return null ;
        return JsonUtil.objectToJson( data ) ;
    }

    protected <T> T deserialize( String data , Class<T> clazz ){
        try{
            return JsonUtil.jsonToBean( data , clazz) ;
        }catch(Exception e){
            log.error("Redis反序列化失败",e);
            return null ;
        }
    }



    @Override
    public <T> T put(String key, T value, Long expire) {
        if( StringUtils.isEmpty( key ) ||  value == null ) {
            return null ;
        }
        if( expire == null || expire <= 0 ){
            jedisCluster.set(key , serialize( value ));
            return value ;
        }
        jedisCluster.psetex(key,expire,serialize( value ));
        return value ;
    }

    @Override
    public boolean exists(String key) {
        if( StringUtils.isEmpty( key ) ){
            return false ;
        }
        boolean exists = jedisCluster.exists( key ) ;
        return exists;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        if( StringUtils.isEmpty( key ) ) {
            return null ;
        }

        T t = deserialize( jedisCluster.get( key ) , clazz ) ;
        return t;
    }

    @Override
    public void del(String key) {
        if( StringUtils.isEmpty( key ) ) {
            return  ;
        }
        jedisCluster.del(key);

    }

    @Override
    public void del(String[] key) {
        if( null==key||key.length<=0 ) {
            return  ;
        }
        jedisCluster.del(key);
    }

    @Override
    public <T> T recache(String key, Class<T> clazz, Long expire) {
        return null;
    }

    @Override
    public Set<String> keys(String pattern) {
        return null;
    }
}
