package com.wxhj.single.device.demo.filter;

import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.filter.DeviceCommonTokenFilter;
import com.wxhj.common.device.model.DeviceResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author daxiong
 * @date 2020/4/30 1:25 下午
 */
@Component
@Slf4j
public class DeviceCommonCheckFilter implements Filter {

    DeviceCommonTokenFilter deviceCommonTokenFilter = new DeviceCommonTokenFilter() {
        @Override
        public boolean hasKey(String key) {
            return false;
        }

        @Override
        public void setKey(String key, String value) {

        }
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (deviceCommonTokenFilter.shouldFilter()) {
            try {
                request = deviceCommonTokenFilter.checkSignature((HttpServletRequest) request);
            } catch (DeviceCommonException e) {
                DeviceResponseState deviceResponseState = e.getDeviceResponseState();
                response.getWriter().write(deviceResponseState.getStandardMessage());
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
