package com.example.demo.common.util;

import com.google.common.base.CaseFormat;

public class CommonUtil {
    public static  String Camel2Underscore (String str){
         str = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
        return str;
    }

    public static  String Underscore2Camel (String str){
        str = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
        return str;
    }

    public static void main(String[] args) {
        String s1="a_b_c";
        System.out.println(Underscore2Camel(s1));
    }
}
