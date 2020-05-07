package com.wxhj.common.device.filter;

import com.google.common.base.Strings;
import com.wxhj.common.device.config.DeviceCommonTokenConfig;
import com.wxhj.common.device.config.DeviceGatewayStaticClass;
import com.wxhj.common.device.constants.DeviceParamStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceCommonRequestWrapper;
import com.wxhj.common.device.model.DeviceResponseState;
import com.wxhj.common.device.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author daxiong
 * @date 2020/4/30 10:00 上午
 */
@Slf4j
public abstract class DeviceCommonTokenFilter {

    DeviceCommonTokenConfig deviceCommonTokenConfig = new DeviceCommonTokenConfig();

    public boolean shouldFilter() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String servletPath = request.getServletPath();
        return DeviceGatewayStaticClass.matchUrl(deviceCommonTokenConfig, servletPath);
    }

    public HttpServletRequest checkSignature(HttpServletRequest request) {
        return checkSignature(request, deviceCommonTokenConfig.getMd5Key());
    }

    public HttpServletRequest checkSignature(HttpServletRequest request, String md5Key) {
        DeviceCommonRequestWrapper deviceCommonRequestWrapper = null;
        String body = null;
        try {
            deviceCommonRequestWrapper = new DeviceCommonRequestWrapper(request);
            body = deviceCommonRequestWrapper.getBodyString();
        } catch (IOException e) {
            throw new DeviceCommonException(DeviceResponseState.INTERNAL_SERVER_ERROR);
        }

        String sign = request.getHeader(DeviceParamStaticClass.SIGN);
        String uuid = request.getHeader(DeviceParamStaticClass.UNIQUE);
        String timestamp = request.getHeader(DeviceParamStaticClass.TIMES_POINT);

        if (Strings.isNullOrEmpty(sign) || Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(timestamp)) {
            throw new DeviceCommonException(DeviceResponseState.SIGNATURE_ERROR);
        }

        // 判断时间是否过期
        boolean checkTimestamp = SignUtil.checkTimestamp(timestamp);
        // 判断uuid在有效期内是否已经存在
        boolean hasKey = hasKey(uuid);
        if (!checkTimestamp || hasKey) {
            throw new DeviceCommonException(DeviceResponseState.SIGNATURE_ERROR);
        }

        if (!SignUtil.checkSign(body, md5Key, uuid, timestamp, sign)) {
            throw new DeviceCommonException(DeviceResponseState.SIGNATURE_ERROR);
        }

        setKey(uuid, uuid);
        return deviceCommonRequestWrapper;
    }

    /**
     * 判断是否有相同的key存在，防止恶意攻击
     *
     * @param key
     * @return boolean
     * @author daxiong
     * @date 2020/5/6 2:27 下午
     */
    public abstract boolean hasKey(String key);

    /**
     * 如果key不存在，则将key保存，注意设置过期时间，要和请求时间间隔相同
     *
     * @param key
     * @param value
     * @return void
     * @author daxiong
     * @date 2020/5/6 2:27 下午
     */
    public abstract void setKey(String key, String value);

}
