package com.example.demo.config.property;

import com.example.demo.common.util.PropertyUtil;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.util.Properties;

import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

@Configuration
public class PropertyConfig extends PropertyPlaceholderConfigurer{

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer=new PropertyPlaceholderConfigurer();

        //PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer=null;
        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources= resolver.getResources(CLASSPATH_URL_PREFIX+"*.properties");
           // propertySourcesPlaceholderConfigurer=new PropertySourcesPlaceholderConfigurer();
            propertyPlaceholderConfigurer.setLocations(resources);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  propertyPlaceholderConfigurer;
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        Properties properties=super.mergeProperties();
        PropertyUtil.setProperties(super.mergeProperties());
        return super.mergeProperties();
    }
}
