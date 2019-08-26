package com.example.demo.common.orm;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCriteria {
    private Object id;
    private String tableName;
    private List<Condition> conditionList;
    private List<UpdateValue> updateValueList;

    public UpdateCriteria(String tableName) {
        this.tableName = tableName;
    }
}
