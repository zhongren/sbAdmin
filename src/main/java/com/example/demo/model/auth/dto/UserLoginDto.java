package com.example.demo.model.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserLoginDto {

    private Integer id;
    private String username;
    @JsonIgnore
    private String passwd;
    private Serializable sid;
    private String realName;    //用户姓名
    private String email;    //邮箱
    private String status;
    @JsonIgnore
    private Set<String> perm;
    @JsonIgnore
    private Set<Integer> role;
}
