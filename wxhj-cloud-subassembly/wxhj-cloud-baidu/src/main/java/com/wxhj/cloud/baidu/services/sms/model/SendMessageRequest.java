/** 
 * @fileName: SendMessageRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:41:25 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import java.util.List;

/**
 * @className SendMessageRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:41:25   
*/
/** 
 * @className SendMessageRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:41:25 
*/
@Deprecated
public class SendMessageRequest {
	   /**
     * The ID of message template. It is unique, whose pattern like this: smsTpl:6nHdNumZ4ZtGaKO.
     */
    private String templateId;
    /**
     * The receiver of message.<br>
     * JSON format, like this:["13800238000","13800138001"]
     */
    private List<String> receiver;
    /**
     * The content variable of message.<br>
     * JSON format, like this: {\"key1\" : \"val1\", \"key2\" : \"val2\"}.
     */
    private String contentVar;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getContentVar() {
        return contentVar;
    }

    public void setContentVar(String contentVar) {
        this.contentVar = contentVar;
    }

    @Override
    public String toString() {
        return "SendMessageRequest [templateId=" + templateId + ", receiver=" + receiver + ", contentVar=" + contentVar
                + "]";
    }

}
