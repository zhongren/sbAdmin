package com.example.demo.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.util.Properties;

/**
 * Created by zhongr on 2017/7/31.
 */
public class PropertyUtil  {

    private Environment environment=ApplicationContextUtil.getApplicationContext().getEnvironment();


    public  String getProperty(String key, String def) {
        return environment.getProperty(key,def);
    }

    public  boolean getProperty(String key, boolean def) {
        return Boolean.parseBoolean(environment.getProperty(key,String.valueOf(def)));
    }

    public  int getProperty(String key, int def) {
        return Integer.parseInt(environment.getProperty(key,String.valueOf(def)));
    }

}
