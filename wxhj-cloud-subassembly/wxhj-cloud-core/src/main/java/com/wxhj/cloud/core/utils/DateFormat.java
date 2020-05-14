package com.wxhj.cloud.core.utils;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.statics.OtherStaticClass;

import java.text.DecimalFormat;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author wxpjf
 * @date 2020/5/12 17:23
 */
public class DateFormat {

    public static void main(String[] args) {
        System.out.println(getStringDate(LocalDateTime.now(), "yyyyMMdd"));
    }

    /**
     * 根据日期格式获取日期Str
     *
     * @param date
     * @param dateFormatStr
     * @return
     */
    public static String getStringDate(TemporalAccessor date, String dateFormatStr) {
        //yyyy-MM-dd HH:mm:ss
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormatStr);
        return fmt.format(date);
    }

    /**
     * 获取日期指定天数后的日期，忽略时分秒
     * 比如输入：2020-04-11 15:00:00，返回2020-04-12 00:00:00
     *
     * @param date
     * @return
     */
    public static LocalDateTime growDateIgnoreHMS(LocalDateTime date) {
        return growDateIgnoreHMS(date, 1);
    }

    /**
     * 获取日期指定天数后的日期，忽略时分秒
     * 比如输入：2020-04-11 15:00:00，返回2020-04-12 00:00:00
     *
     * @param date
     * @param growth
     * @return
     */
    public static LocalDateTime growDateIgnoreHMS(LocalDateTime date, int growth) {
        return date.plusDays(growth).toLocalDate().atStartOfDay();


    }

    /**
     * 设置日期的分钟数
     *
     * @param date
     * @param minuteTotal
     * @return java.lang.String
     * @author daxiong
     * @date 2020/4/15 3:38 下午
     */
    public static LocalDateTime minute2Date(LocalDateTime date, Integer minuteTotal) {
        if (minuteTotal > OtherStaticClass.ONE_DAY_MINUTE) {
            date = date.plusDays(minuteTotal / OtherStaticClass.ONE_DAY_MINUTE);
        }
        minuteTotal = minuteTotal % OtherStaticClass.ONE_DAY_MINUTE;
        int hour = minuteTotal / 60;
        int minute = minuteTotal % 60;

        date = date.withHour(hour);
        date = date.withMinute(minute);
        date = date.withSecond(0);
        return date;
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.set(Calendar.HOUR_OF_DAY, hour);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 0);
//        return c.getTime();
    }

    /**
     * 将日期转换为当天的分钟数，取整
     *
     * @param date
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020-04-11 12:16
     */
    public static Integer date2MinuteTotal(LocalDateTime date) {

        int hour = date.getHour();
        int minute = date.getMinute();
        return hour * 60 + minute;

    }

    /**
     * 根据分钟数获取小时
     * eg: 输入570，返回 9.5小时
     *
     * @param minuteTotal
     * @return java.lang.String
     * @author daxiong
     * @date 2020-04-10 13:42
     */
    public static String minute2Hour(Integer minuteTotal) {
        if (minuteTotal == null || minuteTotal < 0) {
            return null;
        }
        Integer modulusMinute = minuteTotal % OtherStaticClass.ONE_DAY_MINUTE;
        float hour = modulusMinute / 60f;
        String format = new DecimalFormat("#.#").format(hour);
        return format + "小时";
    }

    /**
     * 根据分钟数获取时间
     * eg: 输入540，返回 9:00
     *
     * @param minuteTotal
     * @return java.lang.String
     * @author daxiong
     * @date 2020-04-10 13:42
     */
    public static String minute2HourMinute(Integer minuteTotal) {
        if (minuteTotal == null || minuteTotal < 0) {
            return null;
        }
        Integer modulusMinute = minuteTotal % OtherStaticClass.ONE_DAY_MINUTE;
        int hour = modulusMinute / 60;
        int minute = modulusMinute % 60;
        return Strings.padStart(String.valueOf(hour), 2, '0') + ":" + Strings.padStart(String.valueOf(minute), 2, '0');
    }

//    /**
//     * 根据日期格式获取日期Str
//     *
//     * @param date
//     * @param dateFormatStr
//     * @return
//     */
//    public static String getStringDate(ChronoLocalDate date, String dateFormatStr) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatStr);
//        return date.format(dateTimeFormatter);
//    }
}
