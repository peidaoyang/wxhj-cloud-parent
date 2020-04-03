/** 
 * @fileName: SsoCacheOperation.java  
 * @author: pjf
 * @date: 2019年12月10日 下午3:10:25 
 */

package com.wxhj.cloud.sso;

public interface SsoCacheOperation<T extends IAuthenticationModel> {
	void login(String storeKey, T ssoUser);

	T loginCheck(String sessionId);

	void logout(String sessionId);
	
	void setKey(String key);
	void setExpireMinite(Integer expireMinite);
	
}
