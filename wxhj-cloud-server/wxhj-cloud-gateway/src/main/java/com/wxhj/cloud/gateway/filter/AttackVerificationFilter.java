/** 
 * @fileName: AttackVerificationFilter.java  
 * @author: pjf
 * @date: 2019年10月25日 下午1:34:09 
 */

package com.wxhj.cloud.gateway.filter;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.gateway.config.AttackVerificationConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;

import lombok.extern.slf4j.Slf4j;

/**
 * @className AttackVerificationFilter.java
 * @author pjf
 * @date 2019年10月25日 下午1:34:09
 */
//@Component
@Slf4j
public class AttackVerificationFilter extends ZuulFilter {
	@Resource
	RedisTemplate redisTemplate;

	@Resource
	AttackVerificationConfig attackVerificationConfig;
	private String servletPath;

	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
		servletPath = request.getServletPath();
		return GatewayStaticClass.matchUrl(attackVerificationConfig, servletPath);
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		String attackVerificationKey = request.getHeader(OtherStaticClass.ATTACK_VERIFICATION_HEAD);
		if (Strings.isNullOrEmpty(attackVerificationKey)) {
			context.setResponseBody(WebApiReturnResultModel.ofStatus(WebResponseState.ATTACK_VERIFICATION).toString());
			context.setSendZuulResponse(false);
			return null;
		}
		String redisKey = RedisKeyStaticClass.ATTACK_VERIFICATION_REDIS_KEY + attackVerificationKey;
		if (redisTemplate.hasKey(redisKey)) {
			String redisUrl = (String) redisTemplate.opsForValue().get(redisKey);
			if (servletPath.equals(redisUrl)) {
				context.setResponseBody(
						WebApiReturnResultModel.ofStatus(WebResponseState.ATTACK_VERIFICATION).toString());
				context.setSendZuulResponse(false);
			}
		}
		redisTemplate.opsForValue().set(redisKey, servletPath, attackVerificationConfig.getTimeoutMillisecond(),
				TimeUnit.MILLISECONDS);
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
	}

}
