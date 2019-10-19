package com.example.demo.config.property;

import com.example.demo.common.util.PropertyUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

@Configuration
public class PropertyConfig extends PropertyPlaceholderConfigurer{
    public static Properties properties;

    @Bean
    public PropertyConfig PropertyConfig(){
        PropertyConfig propertyPlaceholderConfigurer=new PropertyConfig();
        List<Properties> propertiesList=new ArrayList<>();
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources= resolver.getResources(CLASSPATH_URL_PREFIX+"*.properties");
            for (Resource resource:resources){
                Properties properties=PropertiesLoaderUtils.loadProperties(resource);
                propertiesList.add(properties);
            }
            Properties[] propertiesArray = new Properties[propertiesList.size()];
            propertyPlaceholderConfigurer.setPropertiesArray(propertiesList.toArray(propertiesArray));
        }catch (Exception e){
            e.printStackTrace();
        }

        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholderConfigurer;
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        properties=super.mergeProperties();
        return properties;
    }
}
