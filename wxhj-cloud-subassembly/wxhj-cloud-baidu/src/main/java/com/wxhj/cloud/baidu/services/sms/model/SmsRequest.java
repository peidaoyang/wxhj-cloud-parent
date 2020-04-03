/** 
 * @fileName: SmsRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:47:11 
 */

package com.wxhj.cloud.baidu.services.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wxhj.cloud.baidu.auth.BceCredentials;
import com.wxhj.cloud.baidu.model.AbstractBceRequest;

/**
 * @className SmsRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:47:11   
*/
/** 
 * @className SmsRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:47:11 
*/
public class SmsRequest extends AbstractBceRequest {
    /*
     * (non-Javadoc)
     * 
     * @see com.wxhj.cloud.baidu.model.AbstractBceRequest#withRequestCredentials(com.wxhj.cloud.baidu .auth.BceCredentials)
     */
    @Override
    public SmsRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

    @Override
    @JsonIgnore
    public BceCredentials getRequestCredentials() {
        return super.getRequestCredentials();
    }

    @Override
    @JsonIgnore
    public void setRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
    }

}
