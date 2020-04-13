package com.wxhj.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.RequestLimitConfig;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class RequestLimitFilter extends ZuulFilter {
    @Resource
    RequestLimitConfig requestLimitConfig;


    private String servletPath;

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
        servletPath = request.getServletPath();
        return GatewayStaticClass.matchUrl(requestLimitConfig, servletPath);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.setResponseBody(WebApiReturnResultModel.ofStatus(WebResponseState.REQUEST_LIMIT).toString());
        context.setSendZuulResponse(false);
        return null;
    }

    @Override
    public String filterType() {

        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {

        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 3;
    }
}
