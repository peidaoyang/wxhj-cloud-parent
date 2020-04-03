/** 
 * @fileName: BceCredentials.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:04:06 
 */

package com.wxhj.cloud.baidu.auth;
/**
 * @className BceCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午9:04:06   
*/
/** 
 * @className BceCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午9:04:06 
*/

public interface BceCredentials {

    /**
     * Returns the BCE access key ID for this credentials object.
     *
     * @return the BCE access key ID for this credentials object.
     */
    public String getAccessKeyId();

    /**
     * Returns the BCE secret access key for this credentials object.
     *
     * @return the BCE secret access key for this credentials object.
     */
    public String getSecretKey();

}