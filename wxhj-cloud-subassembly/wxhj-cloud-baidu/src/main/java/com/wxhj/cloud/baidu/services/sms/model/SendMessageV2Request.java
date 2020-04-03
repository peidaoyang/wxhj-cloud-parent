/** 
 * @fileName: SendMessageV2Request.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:46:22 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @className SendMessageV2Request.java
 * @author pjf
 * @date 2020年2月28日 上午10:46:22   
*/
/** 
 * @className SendMessageV2Request.java
 * @author pjf
 * @date 2020年2月28日 上午10:46:22 
*/

public class SendMessageV2Request extends SmsRequest {

    // ������ID(e.g. r748iAw4-5JwV-nOyT)
    private String invokeId;

    // phoneNumber(e.g. 13800138000)
    private String phoneNumber;

    // templateCode(e.g. smsTpl:d60a7867db0b4225b3aff3d44845d880)
    private String templateCode;

    // contentVar
    private Map<String, String> contentVar;

    public SendMessageV2Request withInvokeId(String invokeId) {
        setInvokeId(invokeId);
        return this;
    }

    public SendMessageV2Request withPhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public SendMessageV2Request withTemplateCode(String templateCode) {
        setTemplateCode(templateCode);
        return this;
    }

    public SendMessageV2Request withContentVar(Map<String, String> contentVar) {
        setContentVar(contentVar);
        return this;
    }

    public SendMessageV2Request addContentVar(String key, String value) {
        if (contentVar == null) {
            contentVar = Maps.newHashMap();
        }
        contentVar.put(key, value);
        return this;
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, String> getContentVar() {
        return contentVar;
    }

    public void setContentVar(Map<String, String> contentVar) {
        this.contentVar = contentVar;
    }

}
