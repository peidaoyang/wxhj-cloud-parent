/** 
 * @fileName: SendMessageResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:42:38 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className SendMessageResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:42:38   
*/
/** 
 * @className SendMessageResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:42:38 
*/

@Deprecated
public class SendMessageResponse extends SmsResponse {
    /**
     * The serial number of message
     */
    private String messageId;

    private SendStatModel sendStat;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public SendStatModel getSendStat() {
        return sendStat;
    }

    public void setSendStat(SendStatModel sendStat) {
        this.sendStat = sendStat;
    }

    @Override
    public String toString() {
        return "SendMessageResponse [messageId=" + messageId + ", sendStat=" + sendStat + "]";
    }

}
