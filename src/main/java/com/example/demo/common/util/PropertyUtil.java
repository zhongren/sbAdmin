package com.example.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by zhongr on 2017/7/31.
 */
public class PropertyUtil  {
    private static Properties properties;

    public static void setProperties(Properties properties) {
        PropertyUtil.properties = properties;
    }

    public static String getProperty(String key, String def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return properties.getProperty(key);
    }

    public static boolean getProperty(String key, boolean def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getProperty(String key, int def) {
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Integer.parseInt(properties.getProperty(key));
    }

}
