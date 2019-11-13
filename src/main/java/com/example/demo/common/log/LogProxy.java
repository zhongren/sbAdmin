package com.example.demo.common.log;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LogProxy  implements MethodInterceptor {
    /**
     * o：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //前置操作
        System.out.println("前置操作");
        Object ob=methodProxy.invokeSuper(o,objects);
        //后置操作
        System.out.println("后置操作");
        return ob;
    }


    public static void main(String[] args) {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(LogConfig.class);
        enhancer.setCallback(new LogProxy());
        LogConfig proxyLog= (LogConfig) enhancer.create();
        proxyLog.autoLog();
    }
}
