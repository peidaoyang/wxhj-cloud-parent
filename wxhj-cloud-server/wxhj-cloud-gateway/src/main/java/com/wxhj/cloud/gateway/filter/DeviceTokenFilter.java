/**
 * @fileName: DeviceTokenFilter.java
 * @author: pjf
 * @date: 2019年10月30日 下午4:59:11
 */

package com.wxhj.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.common.device.constants.DeviceParamStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.filter.DeviceCommonTokenFilter;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;
import com.wxhj.common.device.model.DeviceResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author pjf
 * @className DeviceTokenFilter.java
 * @date 2019年10月30日 下午4:59:11
 */
//@Component
@Slf4j
public class DeviceTokenFilter extends ZuulFilter {
    @Resource
    RedisTemplate redisTemplate;

    DeviceCommonTokenFilter deviceCommonTokenFilter = new DeviceCommonTokenFilter() {
        @Override
        public boolean hasKey(String key) {
            // 判断redis中是否已经有过该uuid
            String signRedisKey = RedisKeyStaticClass.SIGN_UUID + key;
            return redisTemplate.hasKey(signRedisKey);
        }

        @Override
        public void setKey(String key, String value) {
            // redis设置uuid过期时间
            try {
                String signRedisKey = RedisKeyStaticClass.SIGN_UUID + key;
                redisTemplate.opsForValue().set(signRedisKey, key);
                redisTemplate.expire(signRedisKey, DeviceParamStaticClass.TIME_RANGE, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error("redis设置uuid过期时间失败");
                e.printStackTrace();
            }
        }
    };

    @Override
    public boolean shouldFilter() {
        return deviceCommonTokenFilter.shouldFilter();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        try {
            deviceCommonTokenFilter.checkSignature(request);
        } catch (DeviceCommonException e) {
            DeviceResponseState deviceResponseState = e.getDeviceResponseState();
            context.setResponseBody(
                    DeviceApiReturnResultModel.ofStatus(deviceResponseState).toString());
            context.setSendZuulResponse(false);
            return null;
        }
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
