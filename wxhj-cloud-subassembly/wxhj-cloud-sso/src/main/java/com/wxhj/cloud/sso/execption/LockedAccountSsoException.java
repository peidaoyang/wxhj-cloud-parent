/** 
 * @fileName: LockedAccountSsoException.java  
 * @author: pjf
 * @date: 2019年10月14日 下午3:51:38 
 */

package com.wxhj.cloud.sso.execption;

import com.wxhj.cloud.core.enums.WebResponseState;

/**
 * @className LockedAccountSsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:51:38   
*/
/**
 * @className LockedAccountSsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:51:38
 */

public class LockedAccountSsoException extends SsoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 709503736816244031L;

	public LockedAccountSsoException(WebResponseState webResponseState) {
		super(webResponseState);
	}
	public LockedAccountSsoException() {
		this(WebResponseState.ACCOUNT_LOCKER);
	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param message the reason for the exception
	 */
	public LockedAccountSsoException(String message) {
		super(message);
	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param cause the underlying Throwable that caused this exception to be
	 *              thrown.
	 */
	public LockedAccountSsoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param message the reason for the exception
	 * @param cause   the underlying Throwable that caused this exception to be
	 *                thrown.
	 */
	public LockedAccountSsoException(String message, Throwable cause) {
		super(message, cause);
	}
}
