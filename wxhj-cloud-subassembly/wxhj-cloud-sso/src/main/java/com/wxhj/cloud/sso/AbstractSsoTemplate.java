/** 
 * @fileName: AbstractSsoTemplate.java  
 * @author: pjf
 * @date: 2019年12月10日 下午1:05:50 
 */

package com.wxhj.cloud.sso;

import com.wxhj.cloud.sso.bo.SsoLoginBO;
import com.wxhj.cloud.sso.execption.SsoException;

/**
 * @className AbstractSsoTemplate.java
 * @author pjf
 * @date 2019年12月10日 下午1:05:50
 */

public abstract class AbstractSsoTemplate<T extends IAuthenticationModel> {

	public T login(SsoLoginBO ssoLogin) {
		T t = doGetAuthenticationInfo(ssoLogin);

		getSsoCacheOperation().login(t.getUserName(), t);
		return t;
	}

	public T loginCheck(String sessionId) {
		return getSsoCacheOperation().loginCheck(sessionId);
	}

	public void logout(String sessionId) {
		getSsoCacheOperation().logout(sessionId);
	}

	protected abstract SsoCacheOperation<T> getSsoCacheOperation();

	/**
	 * 获取身份校验的信息
	 * 
	 * @author pjf
	 * @date 2019年10月9日 下午4:09:32
	 * @return
	 */
	public abstract T doGetAuthenticationInfo(SsoLoginBO ssoLogin) throws SsoException;

}
