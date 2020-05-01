package com.wxhj.common.device.filter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.wxhj.common.device.config.DeviceCommonTokenConfig;
import com.wxhj.common.device.config.DeviceGatewayStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.ApiRequestModel;
import com.wxhj.common.device.model.DeviceCommonRequestWrapper;
import com.wxhj.common.device.model.DeviceResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author daxiong
 * @date 2020/4/30 10:00 上午
 */
@Component
@Slf4j
public class DeviceCommonTokenFilter {

    @Resource
    DeviceCommonTokenConfig deviceCommonTokenConfig;

    public boolean shouldFilter() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String servletPath = request.getServletPath();
        return DeviceGatewayStaticClass.matchUrl(deviceCommonTokenConfig, servletPath);
    }

    public HttpServletRequest checkSignature(HttpServletRequest request) {
        try {
            DeviceCommonRequestWrapper deviceCommonRequestWrapper = new DeviceCommonRequestWrapper(request);
            String body = deviceCommonRequestWrapper.getBodyString();

            ApiRequestModel apiRequestModel = JSONObject.parseObject(body, ApiRequestModel.class);
            if (!apiRequestModel.checkMd5Signature(deviceCommonTokenConfig.getMd5Key())) {
                throw new DeviceCommonException(DeviceResponseState.SIGNATURE_ERROR);
            }

            String newBody = apiRequestModel.getBizData();
            byte[] reqBodyBytes = newBody.getBytes(Charsets.UTF_8);
            request = new HttpServletRequestWrapper(request) {
                @Override
                public ServletInputStream getInputStream() throws IOException {
                    final ByteArrayInputStream bais = new ByteArrayInputStream(reqBodyBytes);
                    return new ServletInputStream() {
                        @Override
                        public boolean isFinished() {
                            return false;
                        }

                        @Override
                        public boolean isReady() {
                            return false;
                        }

                        @Override
                        public void setReadListener(ReadListener readListener) {
                        }

                        @Override
                        public int read() throws IOException {
                            return bais.read();
                        }
                    };
                }

                @Override
                public int getContentLength() {
                    return reqBodyBytes.length;
                }

                @Override
                public long getContentLengthLong() {
                    return reqBodyBytes.length;
                }
            };

            return request;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new DeviceCommonException(DeviceResponseState.INTERNAL_SERVER_ERROR);
        }
    }

}
