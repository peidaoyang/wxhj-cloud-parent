/** 
 * @fileName: PasswordUtil.java  
 * @author: pjf
 * @date: 2019年10月11日 下午5:10:03 
 */

package com.wxhj.cloud.core.utils;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * @className PasswordUtil.java
 * @author pjf
 * @date 2019年10月11日 下午5:10:03   
*/
/**
 * @className PasswordUtil.java
 * @author pjf
 * @date 2019年10月11日 下午5:10:03
 */
@Slf4j
public class PasswordUtil {

	public static String calculationPassword(String password, String key) {

		key = Md5Util.md5(key).substring(0, 8).toLowerCase();
		try {
			password = DesUtil.encrypt(password, key).toLowerCase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		password = Md5Util.md5(password).substring(0, 32).toLowerCase();
		return password;
	}
	
	public static String generatePasswordKey()
	{
		return UUID.randomUUID().toString().replace("-", "").substring(16);
	}
}
