package com.example.demo.common.orm;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCriteria {
    private String tableName;
    private List<String> fieldList;
    private List<Condition> conditionList;

    public UpdateCriteria(String tableName) {
        this.tableName = tableName;
    }
}
