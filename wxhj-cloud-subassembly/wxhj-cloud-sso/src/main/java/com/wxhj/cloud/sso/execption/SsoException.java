/** 
 * @fileName: SsoException.java  
 * @author: pjf
 * @date: 2019年10月14日 下午3:48:43 
 */

package com.wxhj.cloud.sso.execption;

import com.wxhj.cloud.core.enums.WebResponseState;

/**
 * @className SsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:48:43
 */

public class SsoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8232452454240652745L;
	protected WebResponseState errWebResponseState;

	
	public WebResponseState getWebResponseState()
	{
		return errWebResponseState;
	}
	
	/**
	 * Creates a new AuthenticationException.
	 */
	public SsoException() {
		super();
	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param message the reason for the exception
	 */
	public SsoException(String message) {
		super(message);
	}

	public SsoException(WebResponseState webResponseState) {
		super(webResponseState.getStandardMessage());
		errWebResponseState = webResponseState;

	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param cause the underlying Throwable that caused this exception to be
	 *              thrown.
	 */
	public SsoException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new AuthenticationException.
	 *
	 * @param message the reason for the exception
	 * @param cause   the underlying Throwable that caused this exception to be
	 *                thrown.
	 */
	public SsoException(String message, Throwable cause) {
		super(message, cause);
	}
}
