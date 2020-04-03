/** 
 * @fileName: BceSessionCredentials.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:36:34 
 */

package com.wxhj.cloud.baidu.auth;
/**
 * @className BceSessionCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午10:36:34   
*/
/** 
 * @className BceSessionCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午10:36:34 
*/

public interface BceSessionCredentials extends BceCredentials {

    /**
     * Returns the session token for this credentials object.
     *
     * @return the session token for this credentials object.
     */
    public String getSessionToken();

}