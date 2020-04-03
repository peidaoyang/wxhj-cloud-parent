/** 
 * @fileName: SmsClientConfiguration.java  
 * @author: pjf
 * @date: 2020年2月28日 上午8:39:11 
 */

package com.wxhj.cloud.baidu.services.sms;

import java.net.InetAddress;

import com.wxhj.cloud.baidu.BceClientConfiguration;
import com.wxhj.cloud.baidu.Protocol;
import com.wxhj.cloud.baidu.Region;
import com.wxhj.cloud.baidu.auth.BceCredentials;
import com.wxhj.cloud.baidu.http.RetryPolicy;

/**
 * @className SmsClientConfiguration.java
 * @author pjf
 * @date 2020年2月28日 上午8:39:11
 */

public class SmsClientConfiguration extends BceClientConfiguration {
    @Override
    public SmsClientConfiguration withCredentials(BceCredentials credentials) {
        this.setCredentials(credentials);
        return this;
    }

    @Override
    public SmsClientConfiguration withEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    @Override
    public SmsClientConfiguration withProtocol(Protocol protocol) {
        this.setProtocol(protocol);
        return this;
    }

    @Override
    public SmsClientConfiguration withMaxConnections(int maxConnections) {
        this.setMaxConnections(maxConnections);
        return this;
    }

    @Override
    public SmsClientConfiguration withUserAgent(String userAgent) {
        this.setUserAgent(userAgent);
        return this;
    }

    @Override
    public SmsClientConfiguration withLocalAddress(InetAddress localAddress) {
        this.setLocalAddress(localAddress);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyHost(String proxyHost) {
        this.setProxyHost(proxyHost);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPort(int proxyPort) {
        this.setProxyPort(proxyPort);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyUsername(String proxyUsername) {
        this.setProxyUsername(proxyUsername);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPassword(String proxyPassword) {
        this.setProxyPassword(proxyPassword);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyDomain(String proxyDomain) {
        this.setProxyDomain(proxyDomain);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyWorkstation(String proxyWorkstation) {
        this.setProxyWorkstation(proxyWorkstation);
        return this;
    }

    @Override
    public SmsClientConfiguration withProxyPreemptiveAuthenticationEnabled(
            boolean proxyPreemptiveAuthenticationEnabled) {
        this.setProxyPreemptiveAuthenticationEnabled(proxyPreemptiveAuthenticationEnabled);
        return this;
    }

    @Override
    public SmsClientConfiguration withRetryPolicy(RetryPolicy retryPolicy) {
        this.setRetryPolicy(retryPolicy);
        return this;
    }

    @Override
    public SmsClientConfiguration withSocketTimeoutInMillis(int socketTimeoutInMillis) {
        this.setSocketTimeoutInMillis(socketTimeoutInMillis);
        return this;
    }

    @Override
    public SmsClientConfiguration withConnectionTimeoutInMillis(int connectionTimeoutInMillis) {
        this.setConnectionTimeoutInMillis(connectionTimeoutInMillis);
        return this;
    }

    @Override
    public SmsClientConfiguration withSocketBufferSizeInBytes(int socketBufferSizeInBytes) {
        this.setSocketBufferSizeInBytes(socketBufferSizeInBytes);
        return this;
    }

    @Override
    public SmsClientConfiguration withRegion(Region region) {
        this.setRegion(region);
        return this;
    }
}
