package com.example.demo.common.exception;


import com.example.demo.common.exception.enums.ParamEnum;

/**
 * @author zhongren
 * @date 2017/11/7
 */
public class ParamException extends BusinessException {


    public ParamException(ParamEnum paramEnum) {
        super(paramEnum.getMsg(), paramEnum.getCode());
    }


}
