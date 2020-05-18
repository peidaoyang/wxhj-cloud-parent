/** 
 * @fileName: RedisTemplateConfig.java  
 * @author: pjf
 * @date: 2019年10月9日 下午4:40:45 
 */

package com.wxhj.cloud.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxhj.cloud.core.utils.JSON;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.wxhj.cloud.redis.serializer.FastJsonRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @className RedisTemplateConfig.java
 * @author pjf
 * @date 2019年10月9日 下午4:40:45
 */

@SpringBootConfiguration
@EnableCaching
public class RedisTemplateConfig {
//	@Bean
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<Object, Object> template = new RedisTemplate<>();
//		// 使用fastjson序列化
//		//FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//		Jackson2JsonRedisSerializer serializer=new  Jackson2JsonRedisSerializer(Object.class);
//		// value值的序列化采用fastJsonRedisSerializer
//		template.setValueSerializer(serializer);
//		template.setHashValueSerializer(serializer);
//		// key的序列化采用StringRedisSerializer
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new StringRedisSerializer());
//		template.setConnectionFactory(redisConnectionFactory);
//
//		return template;
//	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		//
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		//objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		JSON.config(objectMapper);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		//使用StringRedisSerializer来序列化和反序列化redis的key值
		RedisSerializer redisSerializer = new StringRedisSerializer();
		//key
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setHashKeySerializer(redisSerializer);
		//value
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

		//

		//
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}


	//

	@Bean
	/* cache key generation strategy */
	public KeyGenerator KeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	@Bean
	/*  redis-cache configuration */
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		//解决查询缓存转换异常的问题
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// 配置序列化（解决乱码的问题）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				// 7 天缓存过期
				.entryTtl(Duration.ofDays(7))
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.disableCachingNullValues();

		RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
				.cacheDefaults(config)
				.build();
		return cacheManager;
	}
}
