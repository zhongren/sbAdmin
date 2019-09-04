package com.example.demo.model.pic.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PicPo {
    private Integer id;
    private String fileName;
    private String filePath;
    private String type;
    private Date createTime;
}
