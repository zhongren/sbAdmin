package com.example.demo.model.auth.dto;


import com.example.demo.common.base.BaseDto;
import lombok.Data;

/**
 * @author zhongren
 * @date 2017/11/9
 */
@Data
public class LoginParam extends BaseDto {
    private String username;
    private String passwd;


}
