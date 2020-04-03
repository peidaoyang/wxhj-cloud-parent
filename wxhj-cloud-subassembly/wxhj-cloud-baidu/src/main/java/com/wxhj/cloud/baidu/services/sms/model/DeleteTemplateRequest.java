/** 
 * @fileName: DeleteTemplateRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:53:32 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import com.wxhj.cloud.baidu.services.ses.model.SesRequest;

/**
 * @className DeleteTemplateRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:53:32   
*/
/** 
 * @className DeleteTemplateRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:53:32 
*/


public class DeleteTemplateRequest extends SesRequest {
    /**
     * The URL parameter<br>
     * The ID of message template. It's unique, and it's pattern like this:smsTpl:6nHdNumZ4ZtGaKO. You have to do
     * URLEncode before using it.
     */
    private String templateId;

    public DeleteTemplateRequest withTemplateId(String templateId) {
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
        return "DeleteTemplateRequest [templateId=" + templateId + "]";
    }

}
