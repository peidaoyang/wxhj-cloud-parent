package com.wxhj.cloud.gateway.filter.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.pool.ThreadPoolHelper;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.SpringUtil;
import com.wxhj.cloud.gateway.config.AppTokenConfig;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import com.wxhj.cloud.gateway.thread.LogAnnotationThread;
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
    @Resource
    DeviceTokenConfig deviceTokenConfig;
    @Resource
    AppTokenConfig appTokenConfig;
    @Resource
    SpringUtil springUtil;
    @Resource
    ThreadPoolHelper threadPoolHelper;

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
        return GatewayStaticClass.matchUrl(webTokenConfig, servletPath)
                || GatewayStaticClass.matchUrl(deviceTokenConfig, servletPath)
                || GatewayStaticClass.matchUrl(appTokenConfig, servletPath);
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String logSessionId = request.getHeader(OtherStaticClass.LOG_SESSION_ID);
        if (logSessionId == null) {
            return null;
        }

        try {
            SsoAuthenticationBO ssoUser = (SsoAuthenticationBO) request.getAttribute(OtherStaticClass.SSO_WEB_HEAD);
            String username = ssoUser == null ? (String) request.getAttribute(DeviceStaticClass.DEVICE_ID)
                    : ssoUser.getUserName();

            String body = request.getReader().lines().collect(Collectors.joining(""));
            String requestURI = UrlUtil.urlFormat(request.getRequestURI());
            // 获取服务名，默认uri第一个就是服务名
            String serverName = requestURI.substring(0, requestURI.indexOf('/'));
            serverName = RedisKeyStaticClass.REDIS_FOLDER_SYMBOL + serverName;
            Object o = redisTemplate.opsForHash().get(RedisKeyStaticClass.LOG_METHOD_INFO_KEY + serverName, requestURI);
            if (o == null) {
                return null;
            }
//            MethodInfo methodInfo = (MethodInfo) o;
            MethodInfo methodInfo = JSONObject.toJavaObject((JSON) o, MethodInfo.class);
            methodInfo.setRequest(body);
            methodInfo.setRequestTime(new Date());
            methodInfo.setUsername(username);
            methodInfo.setId(logSessionId);

            // 异步插入es
            LogAnnotationThread logAnnotationThread = springUtil.getBean(LogAnnotationThread.class);
            logAnnotationThread.setMethodInfo(methodInfo);
            threadPoolHelper.executeTask(logSessionId, logAnnotationThread);
            return null;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}


