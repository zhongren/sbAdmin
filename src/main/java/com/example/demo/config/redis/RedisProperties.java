package com.example.demo.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class RedisProperties {


    private String nodes;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }
}
