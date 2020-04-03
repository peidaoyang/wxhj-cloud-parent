/** 
 * @fileName: RateLimiterFilter.java  
 * @author: pjf
 * @date: 2020年1月10日 下午1:58:41 
 */

package com.wxhj.cloud.gateway.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
/**
 * @className RateLimiterFilter.java
 * @author pjf
 * @date 2020年1月10日 下午1:58:41   
*/
/** 
 * @className RateLimiterFilter.java
 * @author pjf
 * @date 2020年1月10日 下午1:58:41 
*/
@Component
public class RateLimiterFilter extends ZuulFilter{
	public static final RateLimiter RATE_LIMITER = RateLimiter.create(500);
			//RateLimiter.create(10, 2, TimeUnit.SECONDS);
	@Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        boolean flag = RATE_LIMITER.tryAcquire();
        if (!flag) {
        	context.setResponseBody(WebApiReturnResultModel.ofStatus(WebResponseState.RATE_LIMITER).toString());
            context.setSendZuulResponse(false);
        }
        return null;
    }
}
