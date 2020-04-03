/** 
 * @fileName: BceErrorResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:29:43 
 */

package com.wxhj.cloud.baidu;
/**
 * @className BceErrorResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:29:43   
*/
/** 
 * @className BceErrorResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:29:43 
*/

public class BceErrorResponse {

    private String requestId;

    /**
     * The BCE error code which represents the error type.
     */
    private String code;

    /**
     * The detail error message.
     */
    private String message;

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Returns the BCE error code which represents the error type.
     *
     * @return the BCE error code which represents the error type.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the BCE error code which represents the error type.
     *
     * @param code the BCE error code which represents the error type.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the detail error message.
     *
     * @return the detail error message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the detail error message.
     *
     * @param message the detail error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}