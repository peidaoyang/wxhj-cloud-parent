/** 
 * @fileName: QueryMessageDetailRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:49:40 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className QueryMessageDetailRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:49:40   
*/
/** 
 * @className QueryMessageDetailRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:49:40 
*/

public class QueryMessageDetailRequest extends SmsRequest {
    /**
     * The serial number of sending message, build by system.
     */
    private String messageId;

    public QueryMessageDetailRequest withMessageId(String messageId) {
        setMessageId(messageId);
        return this;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "QueryMessageDetailRequest [messageId=" + messageId + "]";
    }

}
