package com.example.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.util.Properties;

/**
 * Created by zhongr on 2017/7/31.
 */
public class PropertyUtil  {
    static String CLASSPATH_URL_PREFIX = "classpath:";
    private static Environment environment=ApplicationContextUtil.applicationContext.getEnvironment();


    public  static String getProperty(String key, String def) {
        return environment.getProperty(key,def);
    }

    public static boolean getProperty(String key, boolean def) {
        return Boolean.parseBoolean(environment.getProperty(key,String.valueOf(def)));
    }

    public static int getProperty(String key, int def) {
        return Integer.parseInt(environment.getProperty(key,String.valueOf(def)));
    }

    public static  String get(String key){

        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources= resolver.getResources(CLASSPATH_URL_PREFIX+"a.properties");
            PropertyPlaceholderConfigurer propertyPlaceholderConfigurer=new PropertyPlaceholderConfigurer();
            propertyPlaceholderConfigurer.setLocations(resources);
        }catch (Exception e){

        }
         return null;
    }
}
