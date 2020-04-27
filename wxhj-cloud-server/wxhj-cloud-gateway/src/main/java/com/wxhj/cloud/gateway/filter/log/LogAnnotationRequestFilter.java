package com.wxhj.cloud.gateway.filter.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import com.wxhj.cloud.redis.annotation.entity.MethodInfo;
import com.wxhj.cloud.redis.annotation.util.UrlUtil;
import com.wxhj.cloud.sso.bo.SsoAuthenticationBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/4/24 1:15 下午
 */
@Slf4j
@Configuration
public class LogAnnotationRequestFilter extends ZuulFilter {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    WebTokenConfig webTokenConfig;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        if (!context.sendZuulResponse()) {
            return false;
        }
        HttpServletRequest request = context.getRequest();
        String servletPath = request.getServletPath();
        return GatewayStaticClass.matchUrl(webTokenConfig, servletPath);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String logSessionId = request.getHeader(OtherStaticClass.LOG_SESSION_ID);
        if (logSessionId == null) {
            return null;
        }

        SsoAuthenticationBO ssoUser = (SsoAuthenticationBO) request.getAttribute(OtherStaticClass.SSO_WEB_HEAD);
        try {
            String body = request.getReader().lines().collect(Collectors.joining(""));
//            String bodyStr = CharStreams.toString(request.getReader());

            String requestURI = UrlUtil.urlFormat(request.getRequestURI());
            Object o = redisTemplate.opsForHash().get(RedisKeyStaticClass.LOG_METHOD_INFO_KEY, requestURI);
            if (o == null) {
                return null;
            }
            MethodInfo methodInfo = (MethodInfo) o;
            methodInfo.setRequest(body);
            methodInfo.setRequestTime(new Date());
            methodInfo.setUsername(ssoUser.getUserName());

            // request信息保存到redis
            redisTemplate.opsForHash().put(RedisKeyStaticClass.LOG_REQUEST_INFO_KEY, logSessionId, methodInfo);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}


