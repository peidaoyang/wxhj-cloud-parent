package com.wxhj.cloud.gateway.filter;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.statics.BusinessStaticClass;
import com.wxhj.common.device.util.SignUtil;
import com.wxhj.cloud.gateway.config.AppTokenConfig;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import com.wxhj.cloud.gateway.util.ServerUtil;
import com.wxhj.common.device.constants.DeviceParamStaticClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * 设备，web，app返回参数加签
 *
 * @className DeviceResponseFilter.java
 * @author pjf
 * @date 2019年11月28日 下午5:29:09
 */
@Component
@Slf4j
public class SignResponseFilter extends ZuulFilter {
	@Resource
	DeviceTokenConfig deviceTokenConfig;
	@Resource
	WebTokenConfig webTokenConfig;
	@Resource
	AppTokenConfig appTokenConfig;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String servletPath = request.getServletPath();
		return GatewayStaticClass.matchUrl(deviceTokenConfig, servletPath)
				|| GatewayStaticClass.matchUrl(webTokenConfig, servletPath)
				|| GatewayStaticClass.matchUrl(appTokenConfig, servletPath);
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletResponse response = context.getResponse();
		HttpServletRequest request = context.getRequest();
		try {
			// 获取返回值内容，加以处理
			InputStream stream = context.getResponseDataStream();
			String body = StreamUtils.copyToString(stream, Charsets.UTF_8);
            body = Strings.isNullOrEmpty(body) ? context.getResponseBody() : body;

			// 加签
			String serverName = ServerUtil.getServerNameFromRequest(request);
			String md5Key = "";
			if (BusinessStaticClass.APP_SERVER.equals(serverName)) {
				md5Key = appTokenConfig.getMd5Key();
			}
			if (BusinessStaticClass.PLATFORM_SERVER.equals(serverName)) {
				md5Key = webTokenConfig.getMd5Key();
			}
			if (BusinessStaticClass.DEVICE_SERVER.equals(serverName)) {
				md5Key = deviceTokenConfig.getMd5Key();
			}
			String sign = SignUtil.sign(body, md5Key);
			response.setHeader(DeviceParamStaticClass.SIGN, sign);

			// 重新设置response的body
			context.setResponseBody(body);
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

//	@Data
//	public class PosReturnTempClass {
//		private Integer code;
//		private String msg;
//		private Object data;
//		private String sign;
//
//		public PosReturnTempClass() {
//		}
//
//		public PosReturnTempClass(WebApiReturnResultModel webApiReturnResultModel) {
//			code = webApiReturnResultModel.getCode();
//			msg = webApiReturnResultModel.getMsg();
//			data = webApiReturnResultModel.getData();
//			String unsign = AlipayCoreUtil.putPairsSequenceAndTogether(this);
//			unsign += deviceTokenConfig.getMd5Key();
//
//			sign=Hashing.md5().newHasher().putString(unsign, Charsets.UTF_8).hash().toString();
//		}
//	}
}
