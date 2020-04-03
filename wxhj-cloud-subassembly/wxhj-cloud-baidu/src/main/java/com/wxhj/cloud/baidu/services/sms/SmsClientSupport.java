/** 
 * @fileName: SmsClientSupport.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:07:09 
 */

package com.wxhj.cloud.baidu.services.sms;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wxhj.cloud.baidu.AbstractBceClient;
import com.wxhj.cloud.baidu.BceClientConfiguration;
import com.wxhj.cloud.baidu.BceClientException;
import com.wxhj.cloud.baidu.auth.BceV1Signer;
import com.wxhj.cloud.baidu.auth.SignOptions;
import com.wxhj.cloud.baidu.http.Headers;
import com.wxhj.cloud.baidu.http.HttpMethodName;
import com.wxhj.cloud.baidu.http.handler.BceErrorResponseHandler;
import com.wxhj.cloud.baidu.http.handler.BceJsonResponseHandler;
import com.wxhj.cloud.baidu.http.handler.BceMetadataResponseHandler;
import com.wxhj.cloud.baidu.http.handler.HttpResponseHandler;
import com.wxhj.cloud.baidu.internal.InternalRequest;
import com.wxhj.cloud.baidu.internal.RestartableInputStream;
import com.wxhj.cloud.baidu.model.AbstractBceRequest;
import com.wxhj.cloud.baidu.util.HttpUtils;

/**
 * @className SmsClientSupport.java
 * @author pjf
 * @date 2020年2月28日 上午9:07:09   
*/
/** 
 * @className SmsClientSupport.java
 * @author pjf
 * @date 2020年2月28日 上午9:07:09 
*/

public abstract class SmsClientSupport extends AbstractBceClient {
    protected static final HttpResponseHandler[] SMS_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler()};

    protected SmsClientSupport(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    /**
     * create general request: by pathPrefix(not contains v1 URL_PREFIX)
     * pathPrefix combined with pathVariables will generate new path
     * For example:/pathPrefix/../pathVariable1/pathVariable2
     *
     * @param pathPrefix    resourcePath
     * @param bceRequest    bceRequest
     * @param httpMethod    method: post���get etc.
     * @param pathVariables variables
     *
     * @return send request message
     */
    protected InternalRequest createGeneralRequest(String pathPrefix, AbstractBceRequest bceRequest,
                                                   HttpMethodName httpMethod, String... pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        assertStringNotNullOrEmpty(pathPrefix, "String resourceKey should not be null or empty");
        pathComponents.add(pathPrefix);
        if (pathVariables != null) {
            Collections.addAll(pathComponents, pathVariables);
        }

        // get a InternalRequest instance
        InternalRequest request =
                new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                        pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(bceRequest.getRequestCredentials());

        // set headersToSign
        SignOptions options = SignOptions.DEFAULT;
        Set<String> headersToSign = new HashSet<String>();
        // headersToSign.add("content-type");
        headersToSign.add("host");
        headersToSign.add("x-bce-date");
        headersToSign.add("x-bce-request-id");
        options.setHeadersToSign(headersToSign);

        new BceV1Signer().sign(request, request.getCredentials(), options);

        return request;
    }

    protected InternalRequest createRequest(String resourceKey, AbstractBceRequest bceRequest,
                                            HttpMethodName httpMethod, String... pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(URL_PREFIX);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        assertStringNotNullOrEmpty(resourceKey, "String resourceKey should not be null or empty");
        pathComponents.add(resourceKey);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        // get a InternalRequest instance
        InternalRequest request =
                new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                        pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(bceRequest.getRequestCredentials());

        // set headersToSign
        SignOptions options = SignOptions.DEFAULT;
        Set<String> headersToSign = new HashSet<String>();
        // headersToSign.add("content-type");
        headersToSign.add("host");
        headersToSign.add("x-bce-date");
        headersToSign.add("x-bce-request-id");
        options.setHeadersToSign(headersToSign);

        new BceV1Signer().sign(request, request.getCredentials(), options);

        return request;
    }

    protected InternalRequest fillRequestPayload(InternalRequest internalRequest, String strJson) {
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, SmsConstant.CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }

    protected void assertStringNotNullOrEmpty(String parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.isEmpty() || parameterValue.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }

    }

    protected void assertStringArrayNotNullOrEmpty(String[] parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.length <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected void assertListNotNullOrEmpty(List<?> parameterValue, String errorMessage) {
        if (parameterValue == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (parameterValue.size() <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}