/** 
 * @fileName: CreateTemplateResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:52:31 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @className CreateTemplateResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:52:31   
*/
/** 
 * @className CreateTemplateResponse.java
 * @author pjf
 * @date 2020年2月28日 上午10:52:31 
*/

public class CreateTemplateResponse extends BaseResponse {

    @JsonInclude(Include.NON_EMPTY)
    private CreateTemplateData data = null;

    public CreateTemplateData getData() {
        return data;
    }

    public void setData(CreateTemplateData data) {
        this.data = data;
    }

    public static class CreateTemplateData {
        String templateId;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("templateId=\"").append(templateId).append("\"");
            sb.append("}");
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMessageV2Response{");
        sb.append("requestId=\"").append(this.getRequestId()).append("\"");
        sb.append(", code=\"").append(this.getCode()).append("\"");
        sb.append(", message=\"").append(this.getMessage()).append("\"");
        sb.append(", data=").append(this.data).append("");
        sb.append("}");
        return sb.toString();
    }

}

