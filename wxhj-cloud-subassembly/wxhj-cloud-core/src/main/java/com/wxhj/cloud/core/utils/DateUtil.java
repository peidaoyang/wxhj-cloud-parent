package com.wxhj.cloud.core.utils;

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
     * sNumber
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

    /**
     * 将日期转换为当天的分钟数，取整
     * @author daxiong
     * @date 2020-04-11 12:16
     * @param date
     * @return java.lang.Integer
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
        return StringUtil.format2Minute(hour) + "：" + StringUtil.format2Minute(minute);
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
        int hour = minuteTotal / 60;
        int minute = minuteTotal % 60;
        return StringUtil.format2Minute(hour) + "：" + StringUtil.format2Minute(minute);
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

}
