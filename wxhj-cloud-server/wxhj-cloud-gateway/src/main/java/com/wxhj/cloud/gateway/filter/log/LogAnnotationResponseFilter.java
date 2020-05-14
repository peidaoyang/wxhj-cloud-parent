package com.wxhj.cloud.gateway.filter.log;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.pool.ThreadPoolHelper;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.time.LocalDateTime;


/**
 * @author daxiong
 * @date 2020/4/24 1:15 下午
 */
@Slf4j
@Configuration
public class LogAnnotationResponseFilter extends ZuulFilter {

    @Resource
    WebTokenConfig webTokenConfig;
    @Resource
    DeviceTokenConfig deviceTokenConfig;
    @Resource
    AppTokenConfig appTokenConfig;
    @Resource
    ThreadPoolHelper threadPoolHelper;
    @Resource
    SpringUtil springUtil;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 3;
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
        HttpServletResponse response = currentContext.getResponse();
        String logSessionId = response.getHeader(OtherStaticClass.LOG_SESSION_ID);
        if (logSessionId == null) {
            return null;
        }
        String requestURI = UrlUtil.urlFormat(currentContext.getRequest().getRequestURI());
        // 获取服务名，默认uri第一个就是服务名
        String serverName = requestURI.substring(0, requestURI.indexOf('/'));
        try {
            InputStream stream = currentContext.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charsets.UTF_8);
            body = Strings.isNullOrEmpty(body) ? currentContext.getResponseBody() : body;
            // body数据已从流中获取，再将body设置到response中
            currentContext.setResponseBody(body);
            MethodInfo methodInfo = MethodInfo.builder()
                    .response(body)
                    .responseTime(LocalDateTime.now()).serverName(serverName).id(logSessionId).build();

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
