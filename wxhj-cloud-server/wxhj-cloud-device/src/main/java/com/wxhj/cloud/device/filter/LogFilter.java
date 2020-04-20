package com.wxhj.cloud.device.filter;


import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author wxpjf
 * @date 2020/4/17 10:06
 */
@Slf4j
@WebFilter(filterName = "LogFilter", urlPatterns = {"/deviceComm/*"})
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogRequestWrapper logRequestWrapper = new LogRequestWrapper((HttpServletRequest) request);
        LogResponseWrapper logResponseWrapper = new LogResponseWrapper((HttpServletResponse) response);
        ServletOutputStream out = response.getOutputStream();
        log.debug(logRequestWrapper.getBodyString());
        chain.doFilter(logRequestWrapper, logResponseWrapper);
        String s = new String(
                logResponseWrapper.getByteArrayOutputStream().toByteArray(), Charsets.UTF_8);
        logResponseWrapper.getByteArrayOutputStream().writeTo(out);
//        // logResponseWrapper
        log.debug(s);
    }

    @Override
    public void destroy() {

    }
}
