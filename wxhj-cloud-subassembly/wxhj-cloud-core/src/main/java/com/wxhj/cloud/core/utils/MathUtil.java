package com.wxhj.cloud.core.utils;

import java.util.Objects;

/**
 * 算数工具类
 *
 * @author daxiong
 * @date 2020/4/16 5:22 下午
 */
public class MathUtil {

    /**
     * 两个数相加，如果传入的为null，则为0
     *
     * @param x
     * @param y
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/4/16 5:32 下午
     */
    public static Integer add(Integer x, Integer y) {
        x = Objects.isNull(x) ? 0 : x;
        y = Objects.isNull(y) ? 0 : y;
        return Math.addExact(x, y);
    }

    /**
     * 多个数相加，如果传入的为null，则为0
     *
     * @param args
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/4/16 5:32 下午
     */
    public static Integer add(Integer... args) {
        Integer total = 0;
        for (Integer arg : args) {
            total = add(total, arg);
        }
        return total;
    }

    /**
     * 两个数相减，如果传入的为null，则为0
     *
     * @param x
     * @param y
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/9 1:09 下午
     */
    public static Integer sub(Integer x, Integer y) {
        x = Objects.isNull(x) ? 0 : x;
        y = Objects.isNull(y) ? 0 : y;
        return Math.subtractExact(x, y);
    }

    /**
     * 两个数相减，如果传入的为null，则为0
     * 如果结果为负数，则返回0
     *
     * @param x
     * @param y
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/9 1:09 下午
     */
    public static Integer subGreatThenZero(Integer x, Integer y) {
        Integer sub = sub(x, y);
        return Math.max(0, sub);
    }

    /**
     * 两个数相减，如果传入的为null，则直接返回0
     *
     * @param x
     * @param y
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/9 1:09 下午
     */
    public static Integer subIfNullZero(Integer x, Integer y) {
        if (Objects.isNull(x) || Objects.isNull(y)) {
            return 0;
        }
        return sub(x, y);
    }

    /**
     * 两个数相除，如果传入的为null，则为0
     *
     * @param x 被除数
     * @param y 除数，如果为null或0，则直接返回0
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/9 1:09 下午
     */
    public static Double div(Double x, Double y) {
        if (y == null || y == 0) {
            return 0D;
        }
        x = Objects.isNull(x) ? 0D : x;
        return x / y;
    }
    /**
     * 两个数相除，如果传入的为null，则为0
     *
     * @param x 被除数
     * @param y 除数，如果为null或0，则直接返回0
     * @return java.lang.Integer
     * @author daxiong
     * @date 2020/5/9 1:09 下午
     */
    public static Double div(Integer x, Integer y) {
        if (y == null || y == 0) {
            return 0D;
        }
        Double dx = Double.valueOf(x);
        Double dy = Double.valueOf(y);
        return div(dx, dy);
    }
}
