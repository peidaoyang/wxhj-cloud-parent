/** 
 * @fileName: DeviceResponseFilter.java  
 * @author: pjf
 * @date: 2019年11月28日 下午5:29:09 
 */

package com.wxhj.cloud.gateway.filter;

import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.AlipayCoreUtil;
import com.wxhj.cloud.core.utils.Md5Util;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @className DeviceResponseFilter.java
 * @author pjf
 * @date 2019年11月28日 下午5:29:09
 */
@Component
@Slf4j
public class DeviceResponseFilter extends ZuulFilter {
	@Resource
	DeviceTokenConfig deviceTokenConfig;
	//@Resource
	//DeviceCommLogService deviceCommLogService;

	private String servletPath;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
		servletPath = request.getServletPath();
		return GatewayStaticClass.matchUrl(deviceTokenConfig, servletPath);
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		try {
			// OutputStream
			// 获取返回值内容，加以处理
			InputStream stream = context.getResponseDataStream();

			String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
			if (Strings.isNullOrEmpty(body)) {
				body = context.getResponseBody();
				if (Strings.isNullOrEmpty(body))
					return null;
			}
			WebApiReturnResultModel webAPIReturnResultModel = JSON.parseObject(body, WebApiReturnResultModel.class);
			PosReturnTempClass posReturnTempClass = new PosReturnTempClass(webAPIReturnResultModel);
			String returnStr = JSON.toJSONString(posReturnTempClass);
			//
//			DeviceCommLogDO deviceCommLog = new DeviceCommLogDO(returnStr, servletPath, "response");
//			deviceCommLogService.insert(deviceCommLog);
			//
			context.setResponseBody(returnStr);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
	}

	@Data
	public class PosReturnTempClass {
		private Integer code;
		private String msg;
		private Object data;
		private String sign;

		public PosReturnTempClass() {
		}

		public PosReturnTempClass(WebApiReturnResultModel webApiReturnResultModel) {
			code = webApiReturnResultModel.getCode();
			msg = webApiReturnResultModel.getMsg();
			data = webApiReturnResultModel.getData();
			String unsign = AlipayCoreUtil.putPairsSequenceAndTogether(this);
			unsign += deviceTokenConfig.getMd5Key();
			sign = Md5Util.md5Encode(unsign, "UTF-8", true);
		}
	}
}
