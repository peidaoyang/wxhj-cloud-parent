/** 
 * @fileName: CreateTemplateRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:51:53 
 */

package com.wxhj.cloud.baidu.services.sms.model;
/**
 * @className CreateTemplateRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:51:53   
*/
/** 
 * @className CreateTemplateRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:51:53 
*/

public class CreateTemplateRequest extends SmsRequest {

    /**
     * signature invokeId, can obtained from console(cloud.baidu.com)
     */
    private String invokeId;

    /**
     * optional;
     * assigned only one signature have different profile
     */
    private String profileId;

    /**
     * The name of message template<br>
     * It's max length is 32 chars, not repeat.
     */
    private String name;

    /**
     * The content of message template<br>
     * It's max length is 300 chars, whose pattern like this:${KEY}, which key is the name of variable. You can make
     * defined as you need, but should be brief as possible as you can.
     */
    private String content;

    public CreateTemplateRequest withInvokeId(String invokeId) {
        setInvokeId(invokeId);
        return this;
    }

    public CreateTemplateRequest withProfileId(String profileId) {
        setProfileId(profileId);
        return this;
    }

    public CreateTemplateRequest withName(String name) {
        setName(name);
        return this;
    }

    public CreateTemplateRequest withContent(String content) {
        setContent(content);
        return this;
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
