package com.example.demo.common.orm;

/**
 * @author zhongren
 * @date 2017/10/31
 */
public class UpdateValue {
    private String column;
    private Object value;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    public UpdateValue(String column, Object value) {
        this.column = column;
        this.value = value;

    }

}
