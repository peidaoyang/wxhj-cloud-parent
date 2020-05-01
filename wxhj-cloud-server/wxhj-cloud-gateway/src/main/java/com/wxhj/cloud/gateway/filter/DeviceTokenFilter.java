/**
 * @fileName: DeviceTokenFilter.java
 * @author: pjf
 * @date: 2019年10月30日 下午4:59:11
 */

package com.wxhj.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.filter.DeviceCommonTokenFilter;
import com.wxhj.common.device.model.ApiRequestModel;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;
import com.wxhj.common.device.model.DeviceCommonRequestWrapper;
import com.wxhj.common.device.model.DeviceResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author pjf
 * @className DeviceTokenFilter.java
 * @date 2019年10月30日 下午4:59:11
 */
@Component
public class DeviceTokenFilter extends ZuulFilter {
    //    @Resource
//    DeviceTokenConfig deviceTokenConfig;
    @Resource
    DeviceCommonTokenFilter deviceCommonTokenFilter;

    private String servletPath;

    @Override
    public boolean shouldFilter() {
//        HttpServletRequest request = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
//        servletPath = request.getServletPath();
//        return GatewayStaticClass.matchUrl(deviceTokenConfig, servletPath);
        return deviceCommonTokenFilter.shouldFilter();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
//        ApiRequestModel apiRequestModel;
//        try {
//            InputStream in = request.getInputStream();
//            //String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//
//            String body = CharStreams.toString(new InputStreamReader(request.getInputStream(), Charsets.UTF_8));
//            // 日志
//
//            apiRequestModel = JSONObject.parseObject(body, ApiRequestModel.class);
//            if (!apiRequestModel.checkMd5Signature(deviceTokenConfig.getMd5Key())) {
//                context.setResponseBody(WebApiReturnResultModel.ofStatus(WebResponseState.SIGNATURE_ERROR).toString());
//                context.setSendZuulResponse(false);
//                return null;
//            }
//            String newBody = apiRequestModel.getBizData();
//            byte[] reqBodyBytes = newBody.getBytes(Charsets.UTF_8);
//            //
//            context.setRequest(new HttpServletRequestWrapper(request) {
//                @Override
//                public ServletInputStream getInputStream() throws IOException {
//                    return new ServletInputStreamWrapper(reqBodyBytes);
//                }
//
//                @Override
//                public int getContentLength() {
//                    return reqBodyBytes.length;
//                }
//
//                @Override
//                public long getContentLengthLong() {
//                    return reqBodyBytes.length;
//                }
//            });

        try {
            String body = StreamUtils.copyToString(request.getInputStream(), Charsets.UTF_8);
            ApiRequestModel apiRequestModel = JSONObject.parseObject(body, ApiRequestModel.class);
            // 将设备id设置到request属性中，后面的日志filter用
            request.setAttribute(DeviceStaticClass.DEVICE_ID, apiRequestModel.getDeviceId());
            request = deviceCommonTokenFilter.checkSignature(request);
            context.setRequest(request);
        } catch (DeviceCommonException e) {
            DeviceResponseState deviceResponseState = e.getDeviceResponseState();
            context.setResponseBody(
                    DeviceApiReturnResultModel.ofStatus(deviceResponseState).toString());
            context.setSendZuulResponse(false);
            return null;
        } catch (IOException ex) {
            context.setResponseBody(
                    WebApiReturnResultModel.ofStatus(WebResponseState.GATEWAY_ERROR, ex.getMessage()).toString());
            context.setSendZuulResponse(false);
            return null;
        }

//        } catch (Exception ex) {
//            context.setResponseBody(
//                    WebApiReturnResultModel.ofStatus(WebResponseState.GATEWAY_ERROR, ex.getMessage()).toString());
//            context.setSendZuulResponse(false);
//            return null;
//        }
        return null;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 2;
    }
}
