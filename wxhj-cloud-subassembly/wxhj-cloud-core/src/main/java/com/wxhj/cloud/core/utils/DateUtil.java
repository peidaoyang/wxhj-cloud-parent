package com.wxhj.cloud.core.utils;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.statics.OtherStaticClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DAY_FORMAT = "yyyy-MM-dd";

    public static Date calcDate(Date date, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    public static int getDateNumber(Date date, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(field);
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getTermDays(Date d1, Date d2) {
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
        try {
            d1 = sdf.parse(sdf.format(d1));
            d2 = sdf.parse(sdf.format(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long days = (d2.getTime() - d1.getTime()) / (24 * 3600 * 1000);
        return Math.abs((int) days);
    }

    public static Date growDate(String dateStr) {
        return growDate(dateStr, 1);
    }

    public static Date growDate(String dateStr, int growth) {
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
        try {
            Date date = sdf.parse(dateStr);
            return growDate(date, growth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date growDate(Date date) {
        return growDate(date, 1);
    }

    /**
     * 获取日期指定天数后的日期
     *
     * @param date
     * @param growth
     * @return
     */
    public static Date growDate(Date date, int growth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, growth);

        Date result = c.getTime();
        return result;
    }

    public static Date growDateMinute(Date date, int growth){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, growth);

        Date result = c.getTime();
        return result;
    }

    /**
     * 获取日期指定天数后的日期，忽略时分秒
     * 比如输入：2020-04-11 15:00:00，返回2020-04-12 00:00:00
     *
     * @param date
     * @return
     */
    public static Date growDateIgnoreHMS(Date date) {
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
    public static Date growDateIgnoreHMS(Date date, int growth) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, growth);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    /**
     * 将日期转换为当天的分钟数，取整
     *
     * @param date
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020-04-11 12:16
     */
    public static Integer date2MinuteTotal(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return hour * 60 + minute;

    }

    /**
     * 获取日期的时分，返回字符串
     * eg：输入：2020-04-10 10:30:30
     * 返回：10:30
     *
     * @param date
     * @return java.lang.String
     * @author daxiong
     * @date 2020-04-10 13:39
     */
    public static String date2HourMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        return Strings.padStart(String.valueOf(hour), 2, '0') + "：" + Strings.padStart(String.valueOf(minute), 2, '0');
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

    /**
     * 获取日期的年
     *
     * @param date
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020-04-10 15:39
     */
    public static Integer getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020-04-10 15:39
     */
    public static Integer getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020-04-10 15:39
     */
    public static Integer getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 将传入的日期设置为当天最晚时间
     * @author daxiong
     * @date 2020/4/15 1:52 下午
     * @param date
     * @return java.util.Date
     */
    public static Date setLatestTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 设置日期的分钟数
     * @author daxiong
     * @date 2020/4/15 3:38 下午
     * @param date
     * @param minuteTotal
     * @return java.lang.String
     */
    public static Date minute2Date(Date date, Integer minuteTotal) {
        if (minuteTotal > OtherStaticClass.ONE_DAY_MINUTE) {
            date = growDate(date);
        }
        minuteTotal = minuteTotal % OtherStaticClass.ONE_DAY_MINUTE;
        int hour = minuteTotal / 60;
        int minute = minuteTotal % 60;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 根据日期格式获取日期Str
     * @param date
     * @param dateFormatStr
     * @return
     */
    public static String getStringDate(Date date,String dateFormatStr){
        //yyyy-MM-dd HH:mm:ss
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        return formatter.format(date);
    }

    public static String getStringDate(long timeStamp){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp * 1000));
    }

}
