package com.wxhj.cloud.core.utils;

import java.text.DecimalFormat;

/**
 * @author daxiong
 * @date 2020-04-11 12:12
 */
public class StringUtil {

    private static final String STR_FORMAT = "00";
    private static DecimalFormat df = new DecimalFormat(STR_FORMAT);

    public static String format2Minute(int time) {
        return df.format(time);
    }
}
