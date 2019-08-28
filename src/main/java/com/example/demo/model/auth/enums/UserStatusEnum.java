package com.example.demo.model.auth.enums;


import com.example.demo.common.dto.ResultDto;

/**
 * Created by zhongr on 2017/8/25.
 */
public enum UserStatusEnum {
    UNACTIVE("0", "禁用"), ACTIVE("1", "激活");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    UserStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
