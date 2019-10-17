package com.example.demo.config.shiro;


import com.example.demo.common.util.WebUtil;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroSimpleCookie extends SimpleCookie {
    public ShiroSimpleCookie(String name) {
        super(name);
    }

    @Override
    public String readValue(HttpServletRequest request, HttpServletResponse ignored) {
        String tokenName=getName();
        String tokenValue= WebUtil.getHeader(request,tokenName);
        if (StringUtils.isEmpty(tokenValue )) {
            return super.readValue(request, ignored);
        }
        return tokenValue;
    }


}
