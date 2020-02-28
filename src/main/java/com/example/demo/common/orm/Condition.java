package com.example.demo.common.orm;

import lombok.Data;

@Data
public class Condition {
    private String column;
    private Object value;
    private String op;

    public Condition(String column, Object value, String op) {
        this.column = column;
        this.value = value;
        this.op = op;
    }

    public Condition(String column, String op) {
        this.column = column;
        this.op = op;
    }

    public Condition(String column, Object value) {
        this.column = column;
        this.value = value;
    }
}
