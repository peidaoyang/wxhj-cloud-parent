/** 
 * @fileName: IncorrectCredentialsSsoException.java  
 * @author: pjf
 * @date: 2019年10月14日 下午3:50:32 
 */

package com.wxhj.cloud.sso.execption;

import com.wxhj.cloud.core.enums.WebResponseState;

/**
 * @className IncorrectCredentialsSsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:50:32   
*/
/**
 * @className IncorrectCredentialsSsoException.java
 * @author pjf
 * @date 2019年10月14日 下午3:50:32
 */

public class IncorrectCredentialsSsoException extends SsoException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5312787249726966682L;

	public IncorrectCredentialsSsoException(WebResponseState webResponseState) {
		super(webResponseState);
	}

	public IncorrectCredentialsSsoException() {
		this(WebResponseState.PASSWORD_ERROR);
	}

	public IncorrectCredentialsSsoException(String message) {
		super(message);
	}

	public IncorrectCredentialsSsoException(Throwable cause) {
		super(cause);
	}

	public IncorrectCredentialsSsoException(String message, Throwable cause) {
		super(message, cause);
	}
}
