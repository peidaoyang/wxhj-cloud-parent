/** 
 * @fileName: BceHttpResponse.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:24:14 
 */

package com.wxhj.cloud.baidu.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.wxhj.cloud.baidu.util.DateUtils;

/**
 * @className BceHttpResponse.java
 * @author pjf
 * @date 2020年2月28日 上午9:24:14   
*/
/**
 * @className BceHttpResponse.java
 * @author pjf
 * @date 2020年2月28日 上午9:24:14
 */

public class BceHttpResponse {
	private static final Logger logger = LoggerFactory.getLogger(BceHttpResponse.class);

	private CloseableHttpResponse httpResponse;

	private InputStream content;

	public BceHttpResponse(CloseableHttpResponse httpResponse) throws IOException {
		this.httpResponse = httpResponse;
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null && entity.isStreaming()) {
			this.content = entity.getContent();
		}
	}

	public String getHeader(String name) {
		Header header = this.httpResponse.getFirstHeader(name);
		if (header == null) {
			return null;
		}
		return header.getValue();
	}

	public long getHeaderAsLong(String name) {
		String value = this.getHeader(name);
		if (value == null) {
			return -1;
		}
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			logger.warn("Invalid " + name + ":" + value, e);
			return -1;
		}
	}

	public Date getHeaderAsRfc822Date(String name) {
		String value = this.getHeader(name);
		if (value == null) {
			return null;
		}
		try {
			return DateUtils.parseRfc822Date(value);
		} catch (Exception e) {
			logger.warn("Invalid " + name + ":" + value, e);
			return null;
		}
	}

	public InputStream getContent() {
		return this.content;
	}

	public String getStatusText() {
		return this.httpResponse.getStatusLine().getReasonPhrase();
	}

	public int getStatusCode() {
		return this.httpResponse.getStatusLine().getStatusCode();
	}

	public CloseableHttpResponse getHttpResponse() {
		return this.httpResponse;
	}

	public Map<String, String> getHeaders() {
		Map<String, String> headers = Maps.newHashMap();
		for (Header header : this.httpResponse.getAllHeaders()) {
			headers.put(header.getName(), header.getValue());
		}
		return headers;
	}

}
