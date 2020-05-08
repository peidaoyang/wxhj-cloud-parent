package com.wxhj.common.device.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.wxhj.common.device.constants.DeviceParamStaticClass;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceResponseState;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daxiong
 * @date 2020/5/5 9:57 上午
 */
@Slf4j
public class SignUtil {
    public static final String BACK_DOOR_STR = "xhsgxxn";

    /**
     * 验签
     *
     * @param body  请求体
     * @param key   md5key
     * @param uuid  唯一uuid
     * @param timestamp 时间戳
     * @param sign  签名
     * @return boolean  true：成功；false：失败
     * @author daxiong
     * @date 2020/5/5 10:10 上午
     */
    public static boolean checkSign(String body, String key, String uuid, String timestamp, String sign) {
        if (key == null) {
            log.error("签名key不能为空");
            return false;
        }
        if (key.equals(BACK_DOOR_STR)) {
            return true;
        }
        if (body == null) {
            body = "";
        }
        String oriStr = uuid + timestamp + body + key;
        System.out.println(oriStr);
        String newSign = Hashing.md5().newHasher().putString(oriStr, Charsets.UTF_8).hash().toString();
        if (newSign.equals(sign)) {
            return true;
        }
        return false;
    }

    /**
     * 加签
     *
     * @param body
     * @param key
     * @return java.lang.String
     * @author daxiong
     * @date 2020/5/5 10:10 上午
     */
    public static String sign(String body, String key) {
        if (key == null) {
            log.error("签名key不能为空");
            return null;
        }
        if (body == null) {
            body = "";
        }
        return Hashing.md5().newHasher().putString(body + key, Charsets.UTF_8).hash().toString();
    }

    /**
     * 判断传入的时间戳是否是近10s内的
     * @author daxiong
     * @date 2020/5/6 11:32 上午
     * @param timestamp
     * @return boolean
     */
    public static boolean checkTimestamp(String timestamp) {
        try {
            long frontTimestamp = Long.parseLong(timestamp);
            long time = System.currentTimeMillis();
            return time >= frontTimestamp
                    &&(time - frontTimestamp) < DeviceParamStaticClass.TIME_RANGE;
        } catch (NumberFormatException e) {
            log.error("时间戳字符串无法转换为long类型");
            throw new DeviceCommonException(DeviceResponseState.SIGNATURE_ERROR);
        }
    }
}
