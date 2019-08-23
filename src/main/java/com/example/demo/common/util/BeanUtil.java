package com.example.demo.common.util;




import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by zr on 2017/8/9.
 */
public class BeanUtil extends cn.hutool.core.bean.BeanUtil {



    public static <T> List<T> convertMap2List(List<Map<String, Object>> list, Class<T> tClass) {
        if (list == null || list.isEmpty() || tClass == null) {
            return null;
        }
        List<T> data = new ArrayList<>();
        for (Map<String, Object> map : list) {
            T t = mapToBean(map,tClass,true);
            data.add(t);
        }
        return data;
    }



}
