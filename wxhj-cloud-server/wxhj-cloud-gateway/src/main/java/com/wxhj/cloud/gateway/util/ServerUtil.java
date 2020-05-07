package com.wxhj.cloud.gateway.util;

import com.google.common.base.Strings;
import com.wxhj.cloud.redis.annotation.util.UrlUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daxiong
 * @date 2020/5/1 4:47 下午
 */
public class ServerUtil {
    /**
     * 从request中获取服务名
     *
     * @param request
     * @return java.lang.String
     * @author daxiong
     * @date 2020/5/1 4:48 下午
     */
    public static String getServerNameFromRequest(HttpServletRequest request) {
        String requestURI = UrlUtil.urlFormat(request.getRequestURI());
        // 获取服务名，默认uri第一个就是服务名
        String serverName = requestURI.substring(0, requestURI.indexOf('/'));
        serverName = Strings.isNullOrEmpty(serverName) ? "" : serverName;
        return serverName;
    }
}
