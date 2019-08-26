package com.example.demo.common.orm;

import lombok.Data;

import java.util.List;

@Data
public class DeleteCriteria {
    private String tableName;
    private List<String> fieldList;
    private List<Condition> conditionList;

    public DeleteCriteria(String tableName) {
        this.tableName = tableName;
    }
}
