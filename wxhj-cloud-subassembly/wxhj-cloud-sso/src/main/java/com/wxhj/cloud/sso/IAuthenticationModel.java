/** 
 * @fileName: IAuthenticationTokenModel.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:15:51 
 */

package com.wxhj.cloud.sso;

/**
 * @className IAuthenticationTokenModel.java
 * @author pjf
 * @date 2019年12月10日 下午1:15:51   
*/
/**
 * @className IAuthenticationTokenModel.java
 * @author pjf
 * @date 2019年12月10日 下午1:15:51
 */

public interface IAuthenticationModel {

	Integer getExpireMinite();

	void setExpireMinite(Integer expireMinite);

	Long getExpireFreshTime();

	void setExpireFreshTime(Long expireFreshTime);

	//
	void setUserName(String userName);

	String getUserName();

	void setUserTocken(String userTocken);

	String getUserTocken();
	
	 void generateTocken();
	 
	 String getSessionId();
}
