/**
 * @fileName: GatewayFallbackProvider.java
 * @author: pjf
 * @date: 2019年10月17日 下午2:15:49
 */

package com.wxhj.cloud.gateway.fallback;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @className GatewayFallbackProvider.java
 * @author pjf
 * @date 2019年10月17日 下午2:15:49
 */
@Component
@Slf4j
public class GatewayFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            log.error(HttpStatus.GATEWAY_TIMEOUT.toString());
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            log.error(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                String responseString = JSON.toJSONString(WebApiReturnResultModel.ofStatus(WebResponseState.ZUUL_DOWN));
//				return new ByteArrayInputStream(responseString.getBytes("UTF-8"));
                return new ByteArrayInputStream(responseString.getBytes(Charsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON,Charsets.UTF_8);
                headers.setContentType(mediaType);
                return headers;
            }
        };
    }
}
