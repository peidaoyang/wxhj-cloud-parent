/** 
 * @fileName: SignOptions.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:17:10 
 */

package com.wxhj.cloud.baidu.auth;

import java.util.Date;
import java.util.Set;

/**
 * @className SignOptions.java
 * @author pjf
 * @date 2020年2月28日 上午9:17:10   
*/
/** 
 * @className SignOptions.java
 * @author pjf
 * @date 2020年2月28日 上午9:17:10 
*/
public class SignOptions {
    /**
     * The default sign options, which is {headersToSign:null, timestamp:null, expirationInSeconds:1800}.
     */
    public static final SignOptions DEFAULT = new SignOptions();

    public static final int DEFAULT_EXPIRATION_IN_SECONDS = 1800;

   // @Deprecated
    //public static final int DEFAULT_MIN_EXPIRATION_IN_SECONDS = 1;

   // @Deprecated
    //public static final int DEFAULT_MAX_EXPIRATION_IN_SECONDS = 2147483647;

    /**
     * The set of headers to be signed.
     */
    private Set<String> headersToSign = null;

    /**
     * The time when the signature was created.
     */
    private Date timestamp = null;

    /**
     * The time until the signature will expire.
     */
    private int expirationInSeconds = DEFAULT_EXPIRATION_IN_SECONDS;

    /**
     * Returns the set of headers to be signed.
     *
     * @return the set of headers to be signed.
     */
    public Set<String> getHeadersToSign() {
        return this.headersToSign;
    }

    /**
     * Sets the set of headers to be signed.
     *
     * @param headersToSign the set of headers to be signed.
     */
    public void setHeadersToSign(Set<String> headersToSign) {
        this.headersToSign = headersToSign;
    }

    /**
     * Returns the time when the signature was created.
     *
     * @return the time when the signature was created.
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets the time when the signature was created.
     *
     * @param timestamp the time when the signature was created.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the time until the signature will expire.
     *
     * @return the time until the signature will expire.
     */
    public int getExpirationInSeconds() {
        return this.expirationInSeconds;
    }

    /**
     * Sets the time until the signature will expire.
     *
     * @param expirationInSeconds The time until the signature will expire.
     */
    public void setExpirationInSeconds(int expirationInSeconds) {
        this.expirationInSeconds = expirationInSeconds;
    }

    @Override
    public String toString() {
        return "SignOptions [\n  headersToSign=" + headersToSign + ",\n  timestamp="
                + timestamp + ",\n  expirationInSeconds=" + expirationInSeconds
                + "]";
    }

}
