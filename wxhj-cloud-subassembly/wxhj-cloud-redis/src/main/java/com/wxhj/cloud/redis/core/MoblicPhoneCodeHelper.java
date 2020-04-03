/** 
 * @fileName: MoblicPhoneCodeHelper.java  
 * @author: pjf
 * @date: 2019年10月29日 下午2:16:40 
 */

package com.wxhj.cloud.redis.core;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.statics.RedisKeyStaticClass;

/**
 * @className MoblicPhoneCodeHelper.java
 * @author pjf
 * @date 2019年10月29日 下午2:16:40
 */

@Component
public class MoblicPhoneCodeHelper {

	private static int redisExpireMinite = 1; // 1 minite
	@Resource
	private RedisTemplate redisTemplate;

	public String moblicPhoneCodeCheck(String moblicPhone) {
		String redisId = RedisKeyStaticClass.MOBILE_PHONE_CODE_REDIS_KEY.concat("#").concat(moblicPhone);
		Object object = redisTemplate.opsForValue().get(redisId);
		if (object == null) {
			return null;
		}
		return object.toString();
	}

	public Boolean moblicPhoneCodeCheck(String moblicPhone, String moblicPhoneCode) {
		String redisMoblicPhoneCode = moblicPhoneCodeCheck(moblicPhone);
		if (redisMoblicPhoneCode == null) {
			return false;
		}
		return redisMoblicPhoneCode.equals(moblicPhoneCode);
	}

	public String generateMoblicPhoneCode(String moblicPhone) {
		String redisId = RedisKeyStaticClass.MOBILE_PHONE_CODE_REDIS_KEY.concat("#").concat(moblicPhone);
		String randomStr = generateRandomNum();
		// 为方便测试暂定1小时有效期
		redisTemplate.opsForValue().set(redisId, randomStr, redisExpireMinite, TimeUnit.MINUTES);
		return randomStr;
	}

	private String generateRandomNum() {
		Random r = new Random();
		return String.valueOf(r.nextInt(900000) + 100000);// (Math.random()*(999999-100000)+100000)
	}
}
