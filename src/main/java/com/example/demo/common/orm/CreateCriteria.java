package com.example.demo.common.orm;

import lombok.Data;

import java.util.List;

@Data
public class CreateCriteria {
    private Object id;
    private String tableName;
    private List<Condition> conditionList;

    public CreateCriteria(String tableName) {
        this.tableName = tableName;
    }
}
