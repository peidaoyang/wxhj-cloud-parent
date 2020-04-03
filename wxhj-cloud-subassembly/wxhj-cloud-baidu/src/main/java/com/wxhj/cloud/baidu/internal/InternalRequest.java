/** 
 * @fileName: InternalRequest.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:08:14 
 */

package com.wxhj.cloud.baidu.internal;

import java.net.URI;
import java.util.Map;

import javax.annotation.concurrent.NotThreadSafe;

import com.google.common.collect.Maps;
import com.wxhj.cloud.baidu.auth.BceCredentials;
import com.wxhj.cloud.baidu.auth.SignOptions;
import com.wxhj.cloud.baidu.http.HttpMethodName;

/**
 * @className InternalRequest.java
 * @author pjf
 * @date 2020年2月28日 上午9:08:14   
*/
/** 
 * @className InternalRequest.java
 * @author pjf
 * @date 2020年2月28日 上午9:08:14 
*/

@NotThreadSafe
public class InternalRequest {

    /**
     * Map of the parameters being sent as part of this request.
     */
    private Map<String, String> parameters = Maps.newHashMap();

    /**
     * Map of the headers included in this request
     */
    private Map<String, String> headers = Maps.newHashMap();

    /**
     * The service endpoint to which this request should be sent
     */
    private URI uri;

    /**
     * The HTTP method to use when sending this request.
     */
    private HttpMethodName httpMethod;

    /**
     * An optional stream from which to read the request payload.
     */
    private RestartableInputStream content;

    private BceCredentials credentials;

    private SignOptions signOptions;

    private boolean expectContinueEnabled;

    public InternalRequest(HttpMethodName httpMethod, URI uri) {
        this.httpMethod = httpMethod;
        this.uri = uri;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public HttpMethodName getHttpMethod() {
        return this.httpMethod;
    }

    public URI getUri() {
        return this.uri;
    }

    public RestartableInputStream getContent() {
        return this.content;
    }

    public void setContent(RestartableInputStream content) {
        this.content = content;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    public BceCredentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(BceCredentials credentials) {
        this.credentials = credentials;
    }

    public SignOptions getSignOptions() {
        return this.signOptions;
    }

    public void setSignOptions(SignOptions signOptions) {
        this.signOptions = signOptions;
    }

    public boolean isExpectContinueEnabled() {
        return this.expectContinueEnabled;
    }

    public void setExpectContinueEnabled(boolean expectContinueEnabled) {
        this.expectContinueEnabled = expectContinueEnabled;
    }

    @Override
    public String toString() {
        return "InternalRequest [httpMethod=" + this.httpMethod + ", uri="  + this.uri + ", "
               + "expectContinueEnabled=" + this.expectContinueEnabled + ", "
               + "parameters=" + this.parameters + ", " + "headers=" + this.headers + "]";
    }
}