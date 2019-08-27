package com.example.demo.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Data
public class PageInfoDto<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer pages;
    private List<T> data;

}
