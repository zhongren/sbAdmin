package com.example.demo.common.exception.exception;


import com.example.demo.common.exception.enums.AuthEnum;

/**
 * Created by zhongr on 2017/8/25.
 */
public class AuthException extends BaseException {

    public AuthException(AuthEnum authEnum) {
        super(authEnum.getMsg(),authEnum.getCode());
    }
}
