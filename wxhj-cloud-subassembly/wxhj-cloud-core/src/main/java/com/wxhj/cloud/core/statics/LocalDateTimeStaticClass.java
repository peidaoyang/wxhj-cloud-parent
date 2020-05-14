package com.wxhj.cloud.core.statics;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author wxpjf
 * @date 2020/5/12 17:05
 */
public class LocalDateTimeStaticClass {
    public static final ZoneOffset LOCAL_ZONE_OFF = ZoneOffset.ofTotalSeconds(28800);

    public static long getTimestamp() {
        return getTimestamp(LocalDateTime.now());
    }
    public static long getTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(LOCAL_ZONE_OFF);
    }
}
