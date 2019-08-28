package com.example.demo.model.user;

import lombok.Data;

@Data
public class UserPo {
    private Integer id;
    private String username;
    private String passwd;
    private String status ;
}
