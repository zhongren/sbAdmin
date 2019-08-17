package com.example.demo.common.orm;

import lombok.Data;

@Data
public class Condition {
    private String field;
    private Object value;
    private String op;

    public Condition(String field, Object value, String op) {
        this.field = field;
        this.value = value;
        this.op = op;
    }
}
