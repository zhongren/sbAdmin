package com.example.demo.model.auth.dto;


import com.example.demo.common.base.BaseDto;
import lombok.Data;

/**
 * Created by zhongr on 2017/8/28.
 */
@Data
public class PermPo extends BaseDto {
    private Integer id;
    private String url;
    private String name;

}
