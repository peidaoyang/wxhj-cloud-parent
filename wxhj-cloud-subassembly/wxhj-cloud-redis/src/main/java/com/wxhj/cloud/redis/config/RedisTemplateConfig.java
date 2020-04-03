/** 
 * @fileName: RedisTemplateConfig.java  
 * @author: pjf
 * @date: 2019年10月9日 下午4:40:45 
 */

package com.wxhj.cloud.redis.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.wxhj.cloud.redis.serializer.FastJsonRedisSerializer;
/**
 * @className RedisTemplateConfig.java
 * @author pjf
 * @date 2019年10月9日 下午4:40:45
 */

@SpringBootConfiguration
@SuppressWarnings("unchecked")
public class RedisTemplateConfig {
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		// 使用fastjson序列化
		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
		// value值的序列化采用fastJsonRedisSerializer
		template.setValueSerializer(fastJsonRedisSerializer);
		template.setHashValueSerializer(fastJsonRedisSerializer);
		// key的序列化采用StringRedisSerializer
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setConnectionFactory(redisConnectionFactory);

		return template;
	}
	//
}
