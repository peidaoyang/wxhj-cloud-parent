/** 
 * @fileName: DefaultBceCredentials.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:03:25 
 */

package com.wxhj.cloud.baidu.auth;
/**
 * @className DefaultBceCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午9:03:25   
*/
/** 
 * @className DefaultBceCredentials.java
 * @author pjf
 * @date 2020年2月28日 上午9:03:25 
*/

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of the Credentials interface that allows callers to pass in the BCE access key and secret
 * access in the constructor.
 */
public class DefaultBceCredentials implements BceCredentials {

    /**
     * The BCE access key ID.
     */
    private final String accessKeyId;

    /**
     * The BCE secret access key.
     */
    private final String secretKey;

    /**
     * Constructs a new Credentials object, with the specified access key id and secret key.
     *
     * @param accessKeyId the BCE access key id.
     * @param secretKey   the BCE secret access key.
     *
     * @throws java.lang.IllegalArgumentException The accessKeyId, secretKey should not be null or empty.
     */
    public DefaultBceCredentials(String accessKeyId, String secretKey) {
        checkNotNull(accessKeyId, "accessKeyId should not be null.");
        checkArgument(!accessKeyId.isEmpty(), "accessKeyId should not be empty.");
        checkNotNull(secretKey, "secretKey should not be null.");
        checkArgument(!secretKey.isEmpty(), "secretKey should not be empty.");

        this.accessKeyId = accessKeyId;
        this.secretKey = secretKey;
    }

    /**
     * @see com.wxhj.cloud.baidu.auth.baidubce.auth.BceCredentials#getAccessKeyId()
     */
    @Override
    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    /**
     * @see com.wxhj.cloud.baidu.auth.baidubce.auth.BceCredentials#getSecretKey()
     */
    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

}