package com.example.demo.common.dto;



import cn.hutool.core.util.StrUtil;
import org.springframework.util.StringUtils;

import java.sql.Struct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class ParamDto extends HashMap<String, Object> {
    public final static String ASC = "ASC";
    public final static String DESC = "DESC";
    public final static String PAGE_SIZE = "pageSize";
    public final static String PAGE_NUM = "pageNum";
    public final static String ORDER_TYPE = "orderType";
    public final static String ORDER_FIELD = "orderField";
    private Integer pageNum;
    private Integer pageSize;
    private String orderType;
    private String orderField;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    private Map<String,String> orderMap = new LinkedHashMap<String, String>() ;

    public Map<String, String> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<String, String> orderMap) {
        this.orderMap = orderMap;
    }



    public String orderBy(){
        if( orderMap.isEmpty() ){
            return null ;
        }
        StringBuilder stringOrderType = new StringBuilder() ;
        for( Entry<String,String> item : orderMap.entrySet() ){
            if( StringUtils.isEmpty( item.getKey() ) || item.getValue() == null ){
                continue ;
            }
            String key= StrUtil.toUnderlineCase(item.getKey());
            stringOrderType.append( String.format(
                    " %s %s ,", key , item.getValue()) ) ;
        }

        String tempstr = stringOrderType.toString().trim() ;
        if( tempstr.endsWith( "," ) ){
            tempstr = tempstr.substring( 0 , tempstr.length() - 1 ) ;
        }
        return tempstr ;
    }

}
