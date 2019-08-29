package com.example.demo.common.exception.exception;


import com.example.demo.common.dto.ResultDto;
import com.example.demo.common.exception.enums.AuthEnum;
import com.example.demo.common.exception.enums.BusinessEnum;

/**
 * Created by zhongr on 2017/8/25.
 */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message, ResultDto.FAIL);
    }
}
