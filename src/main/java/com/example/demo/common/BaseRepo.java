package com.example.demo.common;

import cn.hutool.core.annotation.AnnotationUtil;

import cn.hutool.core.collection.CollectionUtil;
import com.example.demo.common.orm.Condition;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.orm.SearchCriteria;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class BaseRepo {
    private static String FIND = "com.admin.common.orm.BaseMapper.find";
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    private ConditionMap conditionMap;

    public ConditionMap getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(ConditionMap conditionMap) {
        this.conditionMap = conditionMap;
    }

    /**
     * 设置查询条件
     */
    @PostConstruct
    public void init() {

    }

    public SearchCriteria createSearchCriteria(Map<String, Object> param, String... fields) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        if(null!=fields&&fields.length>0){
            List<String> fieldList=Arrays.asList(fields);
            searchCriteria.setFieldList(fieldList);
        }
        return null;
    }

    public List test() {
        List<Map<String, Object>> data = sqlSessionTemplate.selectList("mapper.UserMapper.test");
        return data;
    }
}
