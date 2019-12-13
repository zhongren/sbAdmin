package com.example.demo.common.cache;

import java.util.List;
import java.util.Set;

public interface Cache {

	<T>  T put(String key, T value, Long expire) ;

	boolean exists(String key) ;

	<T> T get(String key, Class<T> clazz) ;

	void del(String key) ;
	void del(String[] key) ;

	<T> T recache(String key, Class<T> clazz, Long expire) ;

	Set<String> keys(String pattern) ;
	
}
