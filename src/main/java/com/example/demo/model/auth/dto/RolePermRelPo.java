package com.example.demo.model.auth.dto;

import com.example.demo.common.base.BaseDto;
import lombok.Data;

/**
 * Created by zhongr on 2017/8/28.
 */
@Data
public class RolePermRelPo extends BaseDto {
    private Integer id;
    private Integer roleId;
    private Integer permId;

}
