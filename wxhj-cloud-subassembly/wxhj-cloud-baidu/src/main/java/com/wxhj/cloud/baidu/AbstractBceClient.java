/** 
 * @fileName: AbstractBceClient.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:37:31 
 */

package com.wxhj.cloud.baidu;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.annotation.concurrent.ThreadSafe;

import com.wxhj.cloud.baidu.auth.BceV1Signer;
import com.wxhj.cloud.baidu.http.BceHttpClient;
import com.wxhj.cloud.baidu.http.Headers;
import com.wxhj.cloud.baidu.http.handler.HttpResponseHandler;
import com.wxhj.cloud.baidu.internal.InternalRequest;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;
import com.wxhj.cloud.baidu.util.DateUtils;

/**
 * @className AbstractBceClient.java
 * @author pjf
 * @date 2020年2月28日 上午9:37:31   
*/
/** 
 * @className AbstractBceClient.java
 * @author pjf
 * @date 2020年2月28日 上午9:37:31 
*/
@ThreadSafe
public abstract class AbstractBceClient {
    /**
     * The default service domain for BCE.
     */
    public static final String DEFAULT_SERVICE_DOMAIN = "baidubce.com";

    /**
     * The common URL prefix for all BCE service APIs.
     */
    public static final String URL_PREFIX = "v1";

    /**
     * The default string encoding for all BCE service APIs.
     */
    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * The default http request content type for all BCE service APIs.
     */
    public static final String DEFAULT_CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * The target service ID.
     */
    private String serviceId;

    /**
     * The endpoint URI for the service.
     */
    private URI endpoint;

    /**
     * Responsible for sending HTTP requests to services.
     */
    private BceHttpClient client;

    /**
     * The client configuration for this client.
     */
    protected BceClientConfiguration config;

    /**
     * A list of handlers for processing HTTP responses from services.
     *
     * @see com.wxhj.cloud.baidu.http.BceHttpClient#execute(InternalRequest, Class, HttpResponseHandler[])
     */
    private HttpResponseHandler[] responseHandlers;

    /**
     * Constructs a new AbstractBceClient with the specified client configuration and handlers.
     * <p>
     * The constructor will extract serviceId from the class name automatically.
     * And if there is no endpoint specified in the client configuration, the constructor will create a default one.
     *
     * @param config the client configuration. The constructor makes a copy of this parameter so that it is
     * safe to change the configuration after then.
     * @param responseHandlers a list of handlers for processing HTTP responses from services. See
     * {@link com.wxhj.cloud.baidu.http.BceHttpClient#execute(InternalRequest, Class, HttpResponseHandler[])}
     * @param isHttpAsyncPutEnabled whether or not PUT method use Async manner.
     * @throws IllegalStateException if the class name does not follow the naming convention for BCE clients.
     * @throws IllegalArgumentException if the endpoint specified in the client configuration is not a valid URI.
     */
    public AbstractBceClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers,
            boolean isHttpAsyncPutEnabled) {
        this.serviceId = this.computeServiceId();
        this.config = config;
        this.endpoint = this.computeEndpoint();
        this.client = new BceHttpClient(config, new BceV1Signer(), isHttpAsyncPutEnabled);
        this.responseHandlers = responseHandlers;
    }

    /**
     * Equivalent to AbstractBceClient(config, responseHandlers, false)
     *
     * @param config the client configuration. The constructor makes a copy of this parameter so that it is
     * safe to change the configuration after then.
     * @param responseHandlers a list of handlers for processing HTTP responses from services. See
     * {@link com.wxhj.cloud.baidu.http.BceHttpClient#execute(InternalRequest, Class, HttpResponseHandler[])}
     * @throws IllegalStateException if the class name does not follow the naming convention for BCE clients.
     * @throws IllegalArgumentException if the endpoint specified in the client configuration is not a valid URI.
     */
    public AbstractBceClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        this(config, responseHandlers, false);
    }

    /**
     * Returns true if the target service supports regions.
     * <p>
     * The result will impact the construction of default service endpoint.
     *
     * @return true if the target service supports regions.
     */
    public boolean isRegionSupported() {
        return true;
    }

    /**
     * Returns the service endpoint to which this client will send requests.
     *
     * @return the service endpoint to which this client will send requests.
     */
    public URI getEndpoint() {
        return this.endpoint;
    }

    /**
     * Returns the target service ID.
     *
     * @return the target service ID.
     */
    public String getServiceId() {
        return this.serviceId;
    }

    public BceHttpClient getClient() {
        return client;
    }

    public void setClient(BceHttpClient client) {
        this.client = client;
    }

    /**
     * Shuts down the client and releases all underlying resources.
     * <p>
     * Invoking this method is NOT a must. Once it is called, no subsequent requests should be made.
     */
    public void shutdown() {
        this.client.shutdown();
    }

    /**
     * Subclasses should invoke this method for sending request to the target service.
     * <p>
     * This method will add "Content-Type" and "Date" to headers with default values if not present.
     *
     * @param request the request to build up the HTTP request.
     * @param responseClass the response class.
     * @param <T> the type of response
     * @return the final response object.
     */
    protected <T extends AbstractBceResponse> T invokeHttpClient(InternalRequest request, Class<T> responseClass) {
        if (!request.getHeaders().containsKey(Headers.CONTENT_TYPE)) {
            request.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        }

        if (!request.getHeaders().containsKey(Headers.DATE)) {
            request.addHeader(Headers.DATE, DateUtils.formatRfc822Date(new Date()));
        }

        return this.client.execute(request, responseClass, this.responseHandlers);
    }

    /**
     * Returns the service ID based on the actual class name.
     * <p>
     * The class name should be in the form of "com.wxhj.cloud.baidu.services.xxx.XxxClient",
     * while "xxx" is the service ID and
     * "Xxx" is the capitalized service ID.
     *
     * @return the computed service ID.
     * @throws IllegalStateException if the class name does not follow the naming convention for BCE clients.
     */
    private String computeServiceId() {
        String packageName = this.getClass().getPackage().getName();
        String prefix = AbstractBceClient.class.getPackage().getName() + ".services.";
        if (!packageName.startsWith(prefix)) {
            throw new IllegalStateException("Unrecognized prefix for the client package : " + packageName + ", "
                    + "'" + prefix + "' expected");
        }
        String serviceId = packageName.substring(prefix.length());
        if (serviceId.indexOf('.') != -1) {
            throw new IllegalStateException("The client class should be put in package like " + prefix + "XXX");
        }
        String className = this.getClass().getName();
        String expectedClassName =
                packageName + '.' + Character.toUpperCase(serviceId.charAt(0)) + serviceId.substring(1) + "Client";
        /**
         * Comment out this verification for media services, since media service is a suit of 
         * services, the media package contains multiple Client classes.
         *
         */
//        if (!className.equals(expectedClassName)) {
//            throw new IllegalStateException("Invalid class name "
//                    + className + ", " + expectedClassName + " expected");
//        }
        return serviceId;
    }

    /**
     * Returns the default target service endpoint.
     * <p>
     * The endpoint will be in the form of "http(s)://<Service ID>[.<Region>].baidubce.com".
     *
     * @return the computed service endpoint
     * @throws IllegalArgumentException if the endpoint specified in the client configuration is not a valid URI.
     */
    private URI computeEndpoint() {
        String endpoint = this.config.getEndpoint();
        try {
            if (endpoint == null) {
                if (this.isRegionSupported()) {
                    endpoint = String.format("%s://%s.%s.%s", this.config.getProtocol(), this.serviceId,
                            this.config.getRegion(), AbstractBceClient.DEFAULT_SERVICE_DOMAIN);
                } else {
                    endpoint = String.format("%s://%s.%s", this.config.getProtocol(), this.serviceId,
                            AbstractBceClient.DEFAULT_SERVICE_DOMAIN);
                }
            }
            return new URI(endpoint);
        } catch (URISyntaxException e) {
            // only if the endpoint specified in the client configuration is not a valid URI, which is not expected.
            throw new IllegalArgumentException("Invalid endpoint." + endpoint, e);
        }
    }
}
