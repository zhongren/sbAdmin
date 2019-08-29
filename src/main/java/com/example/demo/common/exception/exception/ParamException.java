package com.example.demo.common.exception.exception;


import com.example.demo.common.exception.enums.ParamEnum;

/**
 * @author zhongren
 * @date 2017/11/7
 */
public class ParamException extends BaseException {


    public ParamException(ParamEnum paramEnum) {
        super(paramEnum.getMsg(), paramEnum.getCode());
    }


}
