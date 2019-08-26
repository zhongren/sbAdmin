package com.example.demo.common;

import cn.hutool.core.annotation.AnnotationUtil;

import cn.hutool.core.collection.CollectionUtil;
import com.example.demo.common.orm.Condition;
import com.example.demo.common.orm.ConditionMap;
import com.example.demo.common.orm.Op;
import com.example.demo.common.orm.SearchCriteria;
import com.example.demo.common.util.BeanUtil;
import com.example.demo.common.util.CommonUtil;
import com.google.common.base.CaseFormat;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.persistence.Table;
import java.util.*;


public class BaseRepo {
    private static String FIND = "com.example.demo.common.orm.BaseMapper.find";
    private static String CREATE = "com.example.demo.common.orm.BaseMapper.create";
    private static String UPDATE = "com.example.demo.common.orm.BaseMapper.update";
    private static String DELETE = "com.example.demo.common.orm.BaseMapper.delete";
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

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
        if (null != fields && fields.length > 0) {
            List<String> fieldList = Arrays.asList(fields);
            searchCriteria.setColumnList(fieldList);
        }
        if (CollectionUtil.isNotEmpty(param) && CollectionUtil.isNotEmpty(conditionMap)) {
            List<Condition> conditionList = new ArrayList<>();
            for (Map.Entry<String, Condition> entry : getConditionMap().entrySet()) {
                if (param.containsKey(entry.getKey())) {
                    Condition condition = entry.getValue();
                    condition.setValue(param.get(entry.getKey()));
                    conditionList.add(condition);
                }
            }
            searchCriteria.setConditionList(conditionList);
        }
        return searchCriteria;
    }


    private SearchCriteria createSearchCriteria(String field, Object value, String... fields) {
        Table table = AnnotationUtil.getAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        //添加查询字段
        if (null != fields && fields.length > 0) {
            List<String> fieldList = Arrays.asList(fields);
            searchCriteria.setColumnList(fieldList);
        }
        //添加查询条件
        if (!StringUtils.isEmpty(value)) {
            List<Condition> conditionList = new ArrayList<>();
            Condition condition = new Condition(field, value, Op.EQ);
            conditionList.add(condition);
            searchCriteria.setConditionList(conditionList);
        }

        return searchCriteria;
    }

    public List<Map<String, Object>> findMapList(Map<String, Object> param, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(param, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        underscore2Camel(data);
        return data;
    }


    public List<Map<String, Object>> findMapList(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field,value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        underscore2Camel(data);
        return data;
    }

    public Map<String, Object> findMap(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field, value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        if (CollectionUtil.isEmpty(data)) {
            return null;
        }
        underscore2Camel(data);
        return data.get(0);
    }

    public <T> T find(String field, Object value, Class<T> tClass, String... columns) {
        Map<String, Object> map = findMap(field, value, columns);
        return BeanUtil.mapToBean(map, tClass, true);
    }

    public <T> T find(Map<String, Object> param, Class<T> tClass, String... columns) {
        List<T> data = findList( param , tClass ) ;
        if( data == null || data.isEmpty() ){
            return null ;}
        return data.get(0);
    }

    public <T> List<T> findList(String field, Object value, Class<T> tClass, String... columns) {
        List<Map<String, Object>> list = findMapList(field, value, columns);
        return BeanUtil.convertMap2List(list, tClass);
    }


    public <T> List<T> findList(Map<String,Object> param, Class<T> tClass, String... columns) {
        List<Map<String, Object>> list = findMapList(param, columns);
        return BeanUtil.convertMap2List(list, tClass);
    }
    public List test() {
        List<Map<String, Object>> data = sqlSessionTemplate.selectList("mapper.UserMapper.test");
        return data;
    }

    private List<Map<String, Object>> underscore2Camel(List<Map<String, Object>> data){
        for (Map<String, Object> map : data) {
            Map<String, Object> tmpMap = new HashMap<>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                tmpMap.put(CommonUtil.Underscore2Camel(entry.getKey()), entry.getValue());
            }
            map.clear();
            map.putAll(tmpMap);
        }
        return data;
    }
    public static void main(String[] args) {
        String name = "TestStringName";
        name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        System.out.println(name);

    }
}
