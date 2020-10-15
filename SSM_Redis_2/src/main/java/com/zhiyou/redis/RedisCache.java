package com.zhiyou.redis;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

// 缓存配置类
@Configuration		// 说明这个类是一个配置文件
@EnableCaching		// 指定这个类是缓存配置类（开启缓存管理功能）
public class RedisCache extends CachingConfigurerSupport{
	/**
	 *  volatile：（弱同步机制）关键字之一被其修饰的变量发生改变的时候会通知其他线程（当前有线程改变这个对象的值，所有其他线程都会接受到通知）
	 */
	@Autowired
	private volatile JedisConnectionFactory jedisConnectionFactory;
	@Autowired
	private volatile RedisTemplate<String, String> redisTemplate;
	@Autowired
	private volatile RedisCacheManager redisCacheManager;
	
	public RedisCache(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, String> redisTemplate,
			RedisCacheManager redisCacheManager) {
		super();
		this.jedisConnectionFactory = jedisConnectionFactory;
		this.redisTemplate = redisTemplate;
		this.redisCacheManager = redisCacheManager;
	}

	// 由于这些对象spring到时候需要调用，所以提供获取对象的get放法
	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisCacheManager getRedisCacheManager() {
		return redisCacheManager;
	}
	
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			
			public Object generate(Object target, Method method, Object... params) {
				String name = method.getName();
				return name+"lala";
			}
		};
	}
	
	
	
}
