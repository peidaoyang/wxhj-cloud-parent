/** 
 * @fileName: SendMessageV2Response.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:48:01 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className SendMessageV2Response.java
 * @author pjf
 * @date 2020年2月28日 上午10:48:01   
*/
/** 
 * @className SendMessageV2Response.java
 * @author pjf
 * @date 2020年2月28日 上午10:48:01 
*/

/**
 * SendMessageV2Response
 */
public class SendMessageV2Response extends BaseResponse {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMessageV2Response{");
        sb.append("requestId=\"").append(this.getRequestId()).append("\"");
        sb.append(", code=\"").append(this.getCode()).append("\"");
        sb.append(", message=\"").append(this.getMessage()).append("\"");
        sb.append("}");
        return sb.toString();
    }

}