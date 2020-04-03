/** 
 * @fileName: PostFilter.java  
 * @author: pjf
 * @date: 2019年10月11日 下午4:44:06 
 */

package com.wxhj.cloud.gateway.filter.crossDomain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @className PostFilter.java
 * @author pjf
 * @date 2019年10月11日 下午4:44:06   
*/

@Component
public class PostFilter extends ZuulFilter{
	 //private Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        /*
        pre：可以在请求被路由之前调用
        route：在路由请求时候被调用
        post：在route和error过滤器之后被调用
        error：处理请求时发生错误时被调用
        * */
        // 前置过滤器
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //// 优先级为0，数字越大，优先级越低
        return 2;
    }
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //过滤各种POST请求
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        //logger.debug("*****************PostFilter run start*****************");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        HttpServletRequest request = ctx.getRequest();
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token,Accept,Origin");
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Max-Age", "3600");  

        //允许继续路由
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        //logger.debug("*****************PostFilter run end*****************");
        return null;
    }
}
