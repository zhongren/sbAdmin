package com.example.demo.common;


import com.example.demo.common.dto.PageInfoDto;
import com.example.demo.common.dto.ParamDto;
import com.example.demo.common.util.BeanUtil;
import com.example.demo.common.util.PageUtil;
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
    private final String PK = "id";

    private BaseRepo baseRepo;

    public BaseRepo getBaseRepo() {
        return baseRepo;
    }

    public void setBaseRepo(BaseRepo baseRepo) {
        this.baseRepo = baseRepo;
    }

    @PostConstruct
    public abstract void init();

    public <T> PageInfoDto<T> page(ParamDto paramBean, Class<T> clazz) {
        Page page = PageUtil.startPage(paramBean);
        List<Map<String, Object>> data = baseRepo.findMapList(paramBean);
        List<T> list = BeanUtil.convertMap2List(data, clazz);
        PageInfoDto<T> pageInfoBean = new PageInfoDto<>();
        pageInfoBean.setData(list);
        pageInfoBean.setPageNum(page.getPageNum());
        pageInfoBean.setPageSize(page.getPageSize());
        pageInfoBean.setTotal(page.getTotal());
        pageInfoBean.setPages(page.getPages());
        return pageInfoBean;
    }

    public <T> List<T> findList(Map<String, Object> map, Class<T> tClass, String... columns) {
        return baseRepo.findList(map, tClass, columns);
    }

    public <T> List<T> findList(Class<T> tClass, String... columns) {
        return baseRepo.findList(null, tClass, columns);
    }

    public <T> List<T> findList(String field, Object value, Class<T> tClass, String... columns) {
        return baseRepo.findList(field, value, tClass, columns);
    }

    public <T> T find(String field, Object value, Class<T> tClass, String... columns) {
        return baseRepo.find(field, value, tClass, columns);

    }

    public <T> T find(Map<String, Object> param, Class<T> tClass, String... columns) {
        return baseRepo.find(param, tClass, columns);

    }

    public <T> T findById(Long value, Class<T> tClass, String... columns) {
        return baseRepo.find(PK, value, tClass, columns);

    }

    public <T> int create(T bean) {
        return baseRepo.create(bean);
    }

    public <T> int update(String field, Object value, T bean) {
        return baseRepo.update(field, value, bean);
    }

    public int delete(String field, Object value) {
        return baseRepo.delete(field, value);
    }

    public int deleteList(String field, List value) {
        return baseRepo.deleteList(field, value);
    }
}
