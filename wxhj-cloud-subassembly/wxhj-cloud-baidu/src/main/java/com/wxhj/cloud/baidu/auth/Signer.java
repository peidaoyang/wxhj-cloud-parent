/** 
 * @fileName: Signer.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:49:35 
 */

package com.wxhj.cloud.baidu.auth;

import com.wxhj.cloud.baidu.internal.InternalRequest;

/**
 * @className Signer.java
 * @author pjf
 * @date 2020年2月28日 上午9:49:35   
*/
/** 
 * @className Signer.java
 * @author pjf
 * @date 2020年2月28日 上午9:49:35 
*/

public interface Signer {
    /**
     * Equivalent to sign(request, credentials, SignOptions.DEFAULT).
     *
     * @param request     the request to sign.
     * @param credentials the credentials to sign the request with.
     * @throws NullPointerException if any parameter is null.
     */
    public void sign(InternalRequest request, BceCredentials credentials);

    /**
     * Sign the given request with the given set of credentials. Modifies the passed-in request to apply the signature.
     *
     * @param request     the request to sign.
     * @param credentials the credentials to sign the request with.
     * @param options     the options for signing.
     * @throws NullPointerException if any parameter is null.
     */
    public void sign(InternalRequest request, BceCredentials credentials, SignOptions options);
}
