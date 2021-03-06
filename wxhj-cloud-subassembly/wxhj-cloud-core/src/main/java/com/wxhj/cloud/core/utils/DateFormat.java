package com.wxhj.cloud.core.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.statics.OtherStaticClass;
import lombok.Data;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

/**
 * @author wxpjf
 * @date 2020/5/12 17:23
 */
public class DateFormat {

    public static void main(String[] args) {
//        String jsonString ="{\"obj\":{\"str\":[\"123\"]},\"local\":\"2020-05-17 14:48:52\"}";
//        A a = JSON.parseObject(jsonString, A.class);
//        B b1 = JSON.toJavaObject(a.getObj(), B.class);
//        //   String jsonString="";
//        jsonString = JSON.toJSONString(new B());
//        System.out.println(jsonString);
//        B b = JSON.parseObject(jsonString, B.class);
//        System.out.println(b);
//        jsonString = JSON.toJSONString(new A());
//        System.out.println(jsonString);
//         a = JSON.parseObject(jsonString, A.class);
//        System.out.println(a);
//        List<A> aList = Arrays.asList(a);
//        jsonString = JSON.toJSONString(aList);
//        System.out.println(jsonString);
//        aList = JSON.parseArray(jsonString, A.class);
//        System.out.println(aList);
//        // com.alibaba.fastjson.JSON.parseArray()
        LocalDate localDate = growDateIgnoreHMS(LocalDate.now());
        System.out.println(localDate);
    }

    @Data
    static class A {
        @JsonProperty(value = "local")
        LocalDateTime localDateTime = LocalDateTime.now();

        Object obj;
        // Object
    }

    @Data
    static class B {
        private List<String> str = Arrays.asList("123");
    }

    private static final String DAY_FORMAT = "yyyy-MM-dd";

    /**
     * 获取某年的第一天
     *
     * @param year
     * @return java.time.LocalDate
     * @author daxiong
     * @date 2020/5/20 1:43 下午
     */
    public static LocalDate getYearFirst(Integer year) {
        return LocalDate.now().withYear(year).with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * 获取某年的最后一天
     *
     * @param year
     * @return java.time.LocalDate
     * @author daxiong
     * @date 2020/5/20 1:43 下午
     */
    public static LocalDate getYearLast(Integer year) {
        return LocalDate.now().withYear(year).with(TemporalAdjusters.lastDayOfYear());
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

    public static String getStringDate(TemporalAccessor date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DAY_FORMAT);
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
     * 获取日期指定天数后的日期，忽略时分秒，默认增加1天
     * 比如输入：2020-04-11 15:00:00，返回2020-04-12 00:00:00
     *
     * @param date
     * @return
     */
    public static LocalDate growDateIgnoreHMS(LocalDate date) {
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
    public static LocalDate growDateIgnoreHMS(LocalDate date, int growth) {
        return date.plusDays(growth);
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
     * 根据分钟数获取小时
     * eg: 输入570，返回 9.5小时
     *
     * @param minuteTotal
     * @return java.lang.String
     * @author daxiong
     * @date 2020-04-10 13:42
     */
    public static String minute2Hour(Double minuteTotal) {
        if (minuteTotal == null || minuteTotal < 0) {
            return null;
        }
        double modulusMinute = minuteTotal % OtherStaticClass.ONE_DAY_MINUTE;
        double hour = modulusMinute / 60;
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

    public static LocalDate parseDate(String dateStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DAY_FORMAT);
        return LocalDate.parse(dateStr, df);
    }

    /**
     * 判断日期是否在两个目标日期之间
     *
     * @param date
     * @param first
     * @param last
     * @return boolean
     * @author daxiong
     * @date 2020/5/20 1:51 下午
     */
    public static boolean range(LocalDate date, LocalDate first, LocalDate last) {
        return date.isBefore(last) && date.isAfter(first);
    }

    /**
     * 根据指定日期格式将string转成localDateTime
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static LocalDateTime stringToDate(String dateStr,String dateFormat){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDateTime.parse(dateStr, fmt);
    }

}
