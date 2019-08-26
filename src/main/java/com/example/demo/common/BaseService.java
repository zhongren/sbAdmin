package com.example.demo.common;


import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */

public abstract class BaseService {
    private final String PK="id";

    private BaseRepo baseRepo;

    public BaseRepo getBaseRepo() {
        return baseRepo;
    }

    public void setBaseRepo(BaseRepo baseRepo) {
        this.baseRepo = baseRepo;
    }

    @PostConstruct
    public abstract void init();



    public <T> List<T> findList(Map<String,Object> map, Class<T> tClass, String... columns) {
        return baseRepo.findList(map, tClass, columns);
    }
    public <T> List<T> findList(Class<T> tClass, String... columns) {
        return baseRepo.findList(null, tClass, columns);
    }
    public <T> List<T> findList(String field, Object value , Class<T> tClass, String... columns) {
        return baseRepo.findList(field,value, tClass, columns);
    }

    public <T> T find(String field, Object value, Class<T> tClass, String... columns) {
        return baseRepo.find(field, value, tClass, columns);

    }
    public <T> T find(Map<String,Object> param, Class<T> tClass, String... columns) {
        return baseRepo.find(param, tClass, columns);

    }

    public <T> T findById( Long value, Class<T> tClass, String... columns) {
        return baseRepo.find(PK, value, tClass, columns);

    }

}
