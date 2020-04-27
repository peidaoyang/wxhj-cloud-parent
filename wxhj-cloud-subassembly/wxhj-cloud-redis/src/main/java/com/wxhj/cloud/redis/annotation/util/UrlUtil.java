package com.wxhj.cloud.redis.annotation.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * @author daxiong
 * @date 2020/4/24 5:24 下午
 */
public class UrlUtil {
    /**
     * 格式化url
     * @author daxiong
     * @date 2020/4/24 5:17 下午
     * @param oriStr
     * @return java.lang.String
     */
    public static String urlFormat(String oriStr) {
        List<String> strings = Splitter.on('/').omitEmptyStrings().splitToList(oriStr);
        return Joiner.on('/').skipNulls().join(strings);
    }
}
