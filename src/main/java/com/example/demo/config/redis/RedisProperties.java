package com.example.demo.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix="redis" , ignoreUnknownFields = true , ignoreInvalidFields = true )
public class RedisProperties {


	private String address = "127.0.0.1:6379";

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
