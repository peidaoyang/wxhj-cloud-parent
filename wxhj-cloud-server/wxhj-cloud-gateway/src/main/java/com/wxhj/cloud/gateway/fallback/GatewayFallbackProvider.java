/** 
 * @fileName: GatewayFallbackProvider.java  
 * @author: pjf
 * @date: 2019年10月17日 下午2:15:49 
 */

package com.wxhj.cloud.gateway.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

/**
 * @className GatewayFallbackProvider.java
 * @author pjf
 * @date 2019年10月17日 下午2:15:49
 */
@Component
public class GatewayFallbackProvider implements FallbackProvider {
	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
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
				return new ByteArrayInputStream(responseString.getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON, Charset.forName("UTF-8"));
				headers.setContentType(mediaType);
				return headers;
			}
		};
	}
}
