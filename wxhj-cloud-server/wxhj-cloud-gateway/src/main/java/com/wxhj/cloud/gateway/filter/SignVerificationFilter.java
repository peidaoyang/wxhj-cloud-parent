/**
 * @fileName: WebTokenFilter.java
 * @author: pjf
 * @date: 2019年10月11日 下午4:25:41
 */

package com.wxhj.cloud.gateway.filter;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wxhj.cloud.core.statics.BusinessStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.gateway.config.AppTokenConfig;
import com.wxhj.cloud.gateway.config.DeviceTokenConfig;
import com.wxhj.cloud.gateway.config.GatewayStaticClass;
import com.wxhj.cloud.gateway.config.WebTokenConfig;
import com.wxhj.cloud.gateway.util.ServerUtil;
import com.wxhj.common.device.constants.DeviceParamStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.filter.DeviceCommonTokenFilter;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;
import com.wxhj.common.device.model.DeviceResponseState;
import com.wxhj.common.device.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * web、app、设备参数验签
 *
 * @className WebTokenFilter.java
 * @author pjf
 * @date 2019年10月11日 下午4:25:41
 */
@Component
@Slf4j
public class SignVerificationFilter extends ZuulFilter {
    @Resource
    WebTokenConfig webTokenConfig;
    @Resource
    AppTokenConfig appTokenConfig;
    @Resource
    DeviceTokenConfig deviceTokenConfig;
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
                redisTemplate.opsForValue().set(signRedisKey, value);
                redisTemplate.expire(signRedisKey, DeviceParamStaticClass.TIME_RANGE, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error("redis设置uuid过期时间失败");
                e.printStackTrace();
            }
        }
    };

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        if (!context.sendZuulResponse()) {
            return false;
        }
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String servletPath = request.getServletPath();
        return GatewayStaticClass.matchUrl(webTokenConfig, servletPath)
                || GatewayStaticClass.matchUrl(appTokenConfig, servletPath)
                || deviceCommonTokenFilter.shouldFilter();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

//        try {
//            String requestURI = request.getRequestURI();
//            String string = StreamUtils.copyToString(request.getInputStream(), Charsets.UTF_8);
//            System.out.println(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String serverName = ServerUtil.getServerNameFromRequest(request);
        String md5Key;
        if (BusinessStaticClass.APP_SERVER.equals(serverName)) {
            md5Key = appTokenConfig.getMd5Key();
        } else if (BusinessStaticClass.PLATFORM_SERVER.equals(serverName)) {
            md5Key = webTokenConfig.getMd5Key();
        } else {
            md5Key = deviceTokenConfig.getMd5Key();
        }
        if (SignUtil.BACK_DOOR_STR.equals(md5Key)) {
            // 测试模式，不验签
            return null;
        }

//        try {
//            String sign = request.getHeader(SignStaticClass.SIGN);
//            String uuid = request.getHeader(SignStaticClass.UNIQUE);
//            String timestamp = request.getHeader(SignStaticClass.TIMESTAMP);
//
//            if (Strings.isNullOrEmpty(sign) || Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(timestamp)) {
//                context.setResponseBody(
//                        WebApiReturnResultModel.ofStatus(WebResponseState.SIGNATURE_ERROR).toString());
//                context.setSendZuulResponse(false);
//                return null;
//            }
//
//            // 判断时间是否过期
//            boolean checkTimestamp = SignUtil.checkTimestamp(timestamp);
//            // 判断redis中是否已经有过该uuid
//            String signRedisKey = RedisKeyStaticClass.SIGN_UUID + uuid;
//            Boolean hasKey = redisTemplate.hasKey(signRedisKey);
//            if (!checkTimestamp || hasKey) {
//                context.setResponseBody(
//                        WebApiReturnResultModel.ofStatus(WebResponseState.SIGNATURE_ERROR).toString());
//                context.setSendZuulResponse(false);
//                return null;
//            }
//
//            String body = StreamUtils.copyToString(request.getInputStream(), Charsets.UTF_8);
//            body = Strings.isNullOrEmpty(body) ? "" : body;
//
//            if (!SignUtil.checkSign(body, md5Key, uuid, timestamp, sign)) {
//                context.setResponseBody(
//                        WebApiReturnResultModel.ofStatus(WebResponseState.SIGNATURE_ERROR).toString());
//                context.setSendZuulResponse(false);
//                return null;
//            }
//
//            // redis设置uuid过期时间
//            try {
//                redisTemplate.opsForValue().set(signRedisKey, uuid);
//                redisTemplate.expire(signRedisKey, DeviceParamStaticClass.TIME_RANGE, TimeUnit.MILLISECONDS);
//            } catch (Exception e) {
//                log.error("redis设置uuid过期时间失败");
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            context.setResponseBody(
//                    WebApiReturnResultModel.ofStatus(WebResponseState.SIGNATURE_ERROR).toString());
//            context.setSendZuulResponse(false);
//            return null;
//        }

        try {
            deviceCommonTokenFilter.checkSignature(request, md5Key);
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

        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {

        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    public static void main(String[] args) {
//        String body = "{\"code\":200,\"msg\":\"OK\",\"data\":{\"rows\":[{\"id\":\"93d3c501-f132-4e07-a2f9-877c9be595d3\",\"accountId\":\"0000000368\",\"accountName\":\"小明\",\"organizeId\":\"098402fc-b834-4d4a-9d71-ad43760f3cbd\",\"type\":10,\"typeName\":\"年假\",\"duration\":\"\",\"startTime\":\"2020-04-14 00:00:00\",\"endTime\":\"2020-04-18 05:00:00\",\"createTime\":\"2020-04-13 17:01:15\",\"status\":2,\"statusName\":\"审核成功\",\"approveTime\":null,\"reason\":\"出去玩\",\"memo\":null},{\"id\":\"e5422bfe-4029-4244-82ba-f68233559ce3\",\"accountId\":\"0000000368\",\"accountName\":\"小明\",\"organizeId\":\"098402fc-b834-4d4a-9d71-ad43760f3cbd\",\"type\":10,\"typeName\":\"年假\",\"duration\":\"\",\"startTime\":\"2020-04-20 20:00:00\",\"endTime\":\"2020-04-20 23:00:00\",\"createTime\":\"2020-04-13 17:01:15\",\"status\":2,\"statusName\":\"审核成功\",\"approveTime\":null,\"reason\":\"出去玩\",\"memo\":null}],\"page\":1,\"records\":1,\"total\":2}}";
        String body = "{\"authorizeType\": 0,\"deviceId\": \"820122346\"}";
//        String md5Key = "52606F34C985403D850D411BCB600DCD";
        String md5Key = "52606F34C985403D850D411BCB600DCR";
        String unique = "4025d9ab-bcf4-4b35-a2c1-07e05f905570";
        String timestamp = "1588814106000";
//        String sign = SignUtil.sign(body, md5Key);
        String oriStr = unique + timestamp + body + md5Key;
        String sign = Hashing.md5().newHasher().putString(oriStr, Charsets.UTF_8).hash().toString();
        System.out.println(sign);
    }
}
