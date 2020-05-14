package com.wxhj.cloud.core.statics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author wxpjf
 * @date 2020/5/12 17:05
 */
public class LocalDateTimeStaticClass {
    private static final ZoneOffset LOCAL_ZONE_OFF = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
    //ZoneOffset.ofTotalSeconds(28800);

    public static void main(String[] args) {
        System.out.println(getTimestamp());
    }

    public static long getTimestamp() {

        return getTimestamp(LocalDateTime.now());
    }

    public static long getTimestamp(LocalDateTime localDateTime) {

        return localDateTime.toEpochSecond(LOCAL_ZONE_OFF);
    }
}
