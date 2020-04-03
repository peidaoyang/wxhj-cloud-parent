/** 
 * @fileName: SsoRedisCacheOperation.java  
 * @author: pjf
 * @date: 2019年12月10日 下午3:26:03 
 */

package com.wxhj.cloud.sso;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @className SsoRedisCacheOperation.java
 * @author pjf
 * @date 2019年12月10日 下午3:26:03   
*/
/**
 * @className SsoRedisCacheOperation.java
 * @author pjf
 * @date 2019年12月10日 下午3:26:03
 */

@Component
@SuppressWarnings("unchecked")
public class SsoRedisCacheOperation<T extends IAuthenticationModel> implements SsoCacheOperation<T> {
	@Resource
	private RedisTemplate redisTemplate;

	private String redisKey;
	private Integer expireMinite;

	@Override
	public void setKey(String key) {
		redisKey = key;
	}

	@Override
	public void setExpireMinite(Integer expireMinite) {
		this.expireMinite = expireMinite;
	}

	private String parseToken(String sessionId) {
		if (sessionId != null && sessionId.indexOf("_") > -1) {
			String[] sessionIdArr = sessionId.split("_");
			if (sessionIdArr.length == 2 && sessionIdArr[1] != null && sessionIdArr[1].trim().length() > 0) {
				String version = sessionIdArr[1].trim();
				return version;
			}
		}
		return null;
	}

	private T get(String storeKey) {
		String redisKey = redisKey(storeKey);
		T ssoUser = (T) redisTemplate.opsForValue().get(redisKey);
		return ssoUser;
	}

	private String redisKey(String sessionId) {
		return redisKey.concat(sessionId);
	}

	@Override
	public void login(String storeKey, T ssoUser) {
		ssoUser.setExpireMinite(expireMinite);
		ssoUser.setExpireFreshTime(System.currentTimeMillis());
		String redisKey = redisKey(storeKey);

		redisTemplate.opsForValue().set(redisKey, ssoUser, expireMinite, TimeUnit.MINUTES);
	}

	@Override
	public T loginCheck(String sessionId) {
		// 解析seesion获得storeKey
		String storeKey = parseStoreKey(sessionId);
		if (storeKey == null) {
			return null;
		}
		// 根据storeKey去redis中获取ssouser
		T ssoUser = get(storeKey);
		if (ssoUser != null) {
			String token = parseToken(sessionId);
			if (ssoUser.getUserTocken().equals(token)) {
				// 过期时间过了一半后，自动刷新
				if ((System.currentTimeMillis() - ssoUser.getExpireFreshTime()) > ssoUser.getExpireMinite() / 2) {
					ssoUser.setExpireFreshTime(System.currentTimeMillis());
					login(storeKey, ssoUser);
				}
				return ssoUser;
			}
		}
		return null;
	}

	@Override
	public void logout(String sessionId) {

		String storeKey = parseStoreKey(sessionId);
		String redisKey = redisKey(storeKey);
		if (redisTemplate.hasKey(redisKey)) {
			redisTemplate.delete(redisKey);
		}

	}

	/*
	 * 解析存储密钥的key
	 */
	private String parseStoreKey(String sessionId) {
		if (sessionId != null && sessionId.indexOf("_") > -1) {
			String[] sessionIdArr = sessionId.split("_");
			if (sessionIdArr.length == 2 && sessionIdArr[0] != null && sessionIdArr[0].trim().length() > 0) {
				String userId = sessionIdArr[0].trim();
				return userId;
			}
		}
		return null;
	}

//	private void remove(String storeKey) {
//		String redisKey = redisKey(storeKey);
//		redisTemplate.delete(redisKey);
//	}

}
