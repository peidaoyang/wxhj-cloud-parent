/** 
 * @fileName: ErrorCode.java  
 * @author: pjf
 * @date: 2020年2月28日 上午8:49:09 
 */

package com.wxhj.cloud.baidu;

/**
 * @className ErrorCode.java
 * @author pjf
 * @date 2020年2月28日 上午8:49:09   
*/
/**
 * @className ErrorCode.java
 * @author pjf
 * @date 2020年2月28日 上午8:49:09
 */
public enum ErrorCode {
	ACCESS_DENIED("AccessDenied"), INAPPROPRIATE_JSON("InappropriateJSON"), INTERNAL_ERROR("InternalError"),
	INVALID_ACCESS_KEY_ID("InvalidAccessKeyId"), INVALID_HTTP_AUTH_HEADER("InvalidHTTPAuthHeader"),
	INVALID_HTTP_REQUEST("InvalidHTTPRequest"), INVALID_URI("InvalidURI"), MALFORMED_JSON("MalformedJSON"),
	INVALID_VERSION("InvalidVersion"), OPT_IN_REQUIRED("OptInRequired"), PRECONDITION_FAILED("PreconditionFailed"),
	REQUEST_EXPIRED("RequestExpired"), SIGNATURE_DOES_NOT_MATCH("SignatureDoesNotMatch");

	private String code;

	private ErrorCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}

	public boolean equals(String code) {
		return this.code.equals(code);
	}
}