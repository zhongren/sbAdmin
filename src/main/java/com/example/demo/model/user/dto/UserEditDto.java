package com.example.demo.model.user.dto;

import lombok.Data;

@Data
public class UserEditDto {
    private Integer id;
    private String username;
    private String passwd;
    private String status;
}
