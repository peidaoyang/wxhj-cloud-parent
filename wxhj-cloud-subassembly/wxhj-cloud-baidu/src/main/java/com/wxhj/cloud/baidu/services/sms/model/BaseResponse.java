/** 
 * @fileName: BaseResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:48:30 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import com.wxhj.cloud.baidu.model.AbstractBceResponse;
import com.wxhj.cloud.baidu.services.sms.SmsConstant;

/**
 * @className BaseResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:48:30   
*/
/** 
 * @className BaseResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:48:30 
*/

public class BaseResponse extends AbstractBceResponse {

    // requestId(equals messageId)

    private String requestId;

    // return code, success: 1000
    private String code;

    // return message description
    private String message;

    /**
     * return success true not means message is received success
     *
     * @return true: indicate request is success, or fail
     */
    public boolean isSuccess() {
        return SmsConstant.SUCCESS.equals(this.getCode());
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
