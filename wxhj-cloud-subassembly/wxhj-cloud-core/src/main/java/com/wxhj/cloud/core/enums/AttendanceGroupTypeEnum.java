package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-09 17:01
 */
@AllArgsConstructor
@Getter
public enum AttendanceGroupTypeEnum {
    // 按周
    BY_WEEK(0, "按周"),
    // 按月
    BY_MONTH(1, "按月"),
    // 按年
    BY_YEAR(2, "按年"),


    UNKNOWN(404, "未知枚举"),
    ;

    private int code;
    private String desc;

    /**
     * 根据code值获取对应的枚举
     * @param code
     * @return
     */
    public static AttendanceGroupTypeEnum getByCode(int code) {
        for (AttendanceGroupTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
