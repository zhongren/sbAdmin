package com.example.demo.common.exception.enums;


import com.example.demo.common.dto.ResultDto;

/**
 * Created by zhongr on 2017/8/25.
 */
public enum ParamEnum {
    PARAM_LACK(ResultDto.FAIL, "参数错误"), PK_LACK(ResultDto.FAIL, "主键ID缺失");
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

    ParamEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
