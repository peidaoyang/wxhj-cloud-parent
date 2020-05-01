package com.wxhj.cloud.gateway.filter.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.gateway.config.AppTokenConfig;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author daxiong
 * @date 2020/4/26 11:35 上午
 */
@Component
public class LogCommonFilter extends ZuulFilter {

    @Resource
    WebTokenConfig webTokenConfig;
    @Resource
    DeviceTokenConfig deviceTokenConfig;
    @Resource
    AppTokenConfig appTokenConfig;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String servletPath = request.getServletPath();
        return GatewayStaticClass.matchUrl(webTokenConfig, servletPath)
                || GatewayStaticClass.matchUrl(deviceTokenConfig, servletPath)
                || GatewayStaticClass.matchUrl(appTokenConfig, servletPath);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String logSessionId = request.getHeader(OtherStaticClass.LOG_SESSION_ID);
        if (logSessionId != null) {
            response.setHeader(OtherStaticClass.LOG_SESSION_ID, logSessionId);
        }
        return null;
    }
}
