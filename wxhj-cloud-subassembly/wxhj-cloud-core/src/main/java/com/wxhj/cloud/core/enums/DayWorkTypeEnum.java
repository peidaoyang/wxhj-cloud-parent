package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-09 17:01
 */
@AllArgsConstructor
@Getter
public enum  DayWorkTypeEnum {
    // 没有该班次
    NO_ATTENDANCE(-1, "没有改班次"),
    // 工作
    ON_WORK(0, "工作"),
    // 休息
    OFF_WORK(1, "休息"),
    // 请假
    ASK_FOR_LEAVE(2, "请假"),
    // 出差
    ON_BUSINESS(3, "出差"),
    // 其他
    OTHERS(4, "其他"),


    UNKNOWN(404, "未知枚举"),
    ;

    private int code;
    private String desc;

    /**
     * 根据code值获取对应的枚举
     * @param code
     * @return
     */
    public static DayWorkTypeEnum getByCode(int code) {
        for (DayWorkTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
