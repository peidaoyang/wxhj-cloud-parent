/** 
 * @fileName: UnknownAccountSsoException.java  
 * @author: pjf
 * @date: 2019年10月14日 下午3:49:43 
 */

package com.wxhj.cloud.sso.execption;

import com.wxhj.cloud.core.enums.WebResponseState;

/**
 * @className UnknownAccountSsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:49:43   
*/


public class UnknownAccountSsoException extends SsoException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -616087999385158007L;

	public UnknownAccountSsoException(WebResponseState webResponseState) {
		super(webResponseState);
	}
	
	/**
	 * Creates a new UnknownAccountException.
	 */
	public UnknownAccountSsoException() {
		this(WebResponseState.ACCOUNT_ERROR);
	}



	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param message the reason for the exception
	 */
	public UnknownAccountSsoException(String message) {
		super(message);
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param cause the underlying Throwable that caused this exception to be
	 *              thrown.
	 */
	public UnknownAccountSsoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new UnknownAccountException.
	 *
	 * @param message the reason for the exception
	 * @param cause   the underlying Throwable that caused this exception to be
	 *                thrown.
	 */
	public UnknownAccountSsoException(String message, Throwable cause) {
		super(message, cause);
	}
}
