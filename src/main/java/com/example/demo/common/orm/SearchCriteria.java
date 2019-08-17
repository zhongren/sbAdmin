package com.example.demo.common.orm;

import lombok.Data;

import java.util.List;
@Data
public class SearchCriteria {
    private String tableName;
    private List<String> fieldList;
    private List<Condition> conditionList;

    public SearchCriteria(String tableName) {
        this.tableName = tableName;
    }
}
