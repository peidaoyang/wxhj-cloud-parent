/** 
 * @fileName: SesRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午11:20:17 
 */

package com.wxhj.cloud.baidu.services.ses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wxhj.cloud.baidu.auth.BceCredentials;
import com.wxhj.cloud.baidu.model.AbstractBceRequest;

/**
 * @className SesRequest.java
 * @author pjf
 * @date 2020年2月28日 上午11:20:17   
*/
/** 
 * @className SesRequest.java
 * @author pjf
 * @date 2020年2月28日 上午11:20:17 
*/

public class SesRequest extends AbstractBceRequest {

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

    @Override
    public SesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
