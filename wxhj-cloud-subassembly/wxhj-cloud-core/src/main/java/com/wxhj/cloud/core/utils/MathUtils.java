package com.wxhj.cloud.core.utils;

import java.util.Objects;

/**
 * 算数工具类
 * @author daxiong
 * @date 2020/4/16 5:22 下午
 */
public class MathUtils {

    /**
     * 两个数相加，如果传入的为null，则为0
     * @author daxiong
     * @date 2020/4/16 5:32 下午
     * @param x
     * @param y
     * @return java.lang.Integer
     */
    public static Integer add(Integer x, Integer y) {
        x = Objects.isNull(x) ? 0 : x;
        y = Objects.isNull(y) ? 0 : y;
        return Math.addExact(x, y);
    }

    /**
     * 多个数相加，如果传入的为null，则为0
     * @author daxiong
     * @date 2020/4/16 5:32 下午
     * @param args
     * @return java.lang.Integer
     */
    public static Integer add(Integer... args) {
        Integer total = 0;
        for (Integer arg : args) {
            total = add(total, arg);
        }
        return total;
    }

}
