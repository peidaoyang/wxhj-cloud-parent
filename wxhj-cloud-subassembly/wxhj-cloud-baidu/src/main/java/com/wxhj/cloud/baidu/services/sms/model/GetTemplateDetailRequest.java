/** 
 * @fileName: GetTemplateDetailRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:59:33 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className GetTemplateDetailRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:59:33   
*/
/** 
 * @className GetTemplateDetailRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:59:33 
*/

public class GetTemplateDetailRequest extends SmsRequest {
    /**
     * The URL parameter<br>
     * The ID of message template. It's unique, and it's pattern like this:smsTpl:6nHdNumZ4ZtGaKO. You have to do
     * URLEncode before using it.
     */
    private String templateId;

    public GetTemplateDetailRequest withTemplateId(String templateId) {
        setTemplateId(templateId);
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "GetTemplateDetailRequest [templateId=" + templateId + "]";
    }

}
