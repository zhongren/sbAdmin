package com.example.demo.common.util;

import com.example.demo.config.property.PropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
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

public class PropertyUtil {


    public static String getProperty(String key, String def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return properties.getProperty(key);
    }


    public static boolean getProperty(String key, boolean def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getProperty(String key, int def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Integer.parseInt(properties.getProperty(key));
    }

}
