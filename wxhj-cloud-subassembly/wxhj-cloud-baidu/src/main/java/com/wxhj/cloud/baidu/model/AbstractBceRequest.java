/** 
 * @fileName: AbstractBceRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:24:59 
 */

package com.wxhj.cloud.baidu.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wxhj.cloud.baidu.auth.BceCredentials;

/**
 * @className AbstractBceRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:24:59   
*/
/** 
 * @className AbstractBceRequest.java
 * @author pjf
 * @date 2020年2月28日 上午10:24:59 
*/

//@NotThreadSafe
public abstract class AbstractBceRequest {

    /**
     * The optional credentials to use for this request - overrides the default credentials set at the client level.
     */
    private BceCredentials credentials;

    /**
     * Returns the optional credentials to use to sign this request, overriding the default credentials set at the
     * client level.
     *
     * @return The optional credentials to use to sign this request, overriding the default credentials set at the
     * client level.
     */
    @JsonIgnore
    public BceCredentials getRequestCredentials() {
        return this.credentials;
    }

    /**
     * Sets the optional credentials to use for this request, overriding the default credentials set at the client
     * level.
     *
     * @param credentials The optional BCE security credentials to use for this request, overriding the default
     *                    credentials set at the client level.
     */
    @JsonIgnore
    public void setRequestCredentials(BceCredentials credentials) {
        this.credentials = credentials;
    }

    public abstract AbstractBceRequest withRequestCredentials(BceCredentials credentials);
}