package com.example.demo.model.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserPo {
    private Integer id;
    private String username;
    private String passwd;
    private String status;
    private Date createTime;
}
