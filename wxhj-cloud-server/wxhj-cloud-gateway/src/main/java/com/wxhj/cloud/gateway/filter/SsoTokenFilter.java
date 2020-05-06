/** 
 * @fileName: WebTokenFilter.java  
 * @author: pjf
 * @date: 2019年10月11日 下午4:25:41 
 */

package com.wxhj.cloud.gateway.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.wxhj.cloud.core.statics.BusinessStaticClass;
import com.wxhj.cloud.gateway.config.AppTokenConfig;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.redis.annotation.util.UrlUtil;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import com.wxhj.cloud.sso.SsoCacheOperation;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;

import lombok.extern.slf4j.Slf4j;

/**
 * @className WebTokenFilter.java
 * @author pjf
 * @date 2019年10月11日 下午4:25:41
 */

@Component
@Slf4j
public class SsoTokenFilter extends ZuulFilter {
	@Resource
	WebTokenConfig webTokenConfig;
	@Resource
	AppTokenConfig appTokenConfig;
	@Resource
	SsoCacheOperation<SsoAuthenticationBO> ssoCacheOperation;

	private String servletPath;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		servletPath = request.getServletPath();
		return GatewayStaticClass.matchUrl(webTokenConfig, servletPath)
				|| GatewayStaticClass.matchUrl(appTokenConfig, servletPath);
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();

		String requestURI = UrlUtil.urlFormat(request.getRequestURI());
		// 获取服务名，默认uri第一个就是服务名
		String serverName = requestURI.substring(0, requestURI.indexOf('/'));
		String cacheKey = BusinessStaticClass.APP_SERVER.equals(serverName) ?
				RedisKeyStaticClass.SSO_APP_USER : RedisKeyStaticClass.SSO_USER;

		String sessionId = request.getHeader(OtherStaticClass.SSO_WEB_HEAD);
		ssoCacheOperation.setKey(cacheKey);
		ssoCacheOperation.setExpireMinite(OtherStaticClass.SSO_REDIS_EXPIRE_MINITE);
		SsoAuthenticationBO ssoUser = ssoCacheOperation.loginCheck(sessionId);
		//
		if (ssoUser == null) {
//			context.setResponseBody(WebApiReturnResultModel.ofStatus(WebResponseState.NOT_LOGIN).toString());
//			context.setSendZuulResponse(false);
//			return null;
			ssoUser = new SsoAuthenticationBO();
			ssoUser.setUserName("测试");
		}
		request.setAttribute(OtherStaticClass.SSO_WEB_HEAD, ssoUser);

		return null;
	}

	@Override
	public String filterType() {

		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {

		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}
}