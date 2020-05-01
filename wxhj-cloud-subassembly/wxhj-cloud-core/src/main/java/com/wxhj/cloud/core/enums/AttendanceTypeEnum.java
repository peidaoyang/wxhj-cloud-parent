package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-09 17:01
 */
@AllArgsConstructor
@Getter
public enum AttendanceTypeEnum {
    // 工作
    NORMAL(0, "正常"),
    // 迟到
    LATE_WORK(1, "迟到"),
    // 早退
    EARLY_OFF(2, "早退"),
    // 迟到早退
    LATE_EARLY(3, "迟到早退"),
    // 上班缺卡
    UP_ABSENCE(4, "上班缺卡"),
    // 下班缺卡
    DOWN_ABSENCE(5, "下班缺卡"),
    // 旷工
    UP_DOWN_ABSENCE(6, "旷工"),


    UNKNOWN(404, "未知枚举"),
    ;

    private int code;
    private String desc;

    /**
     * 根据code值获取对应的枚举
     * @param code
     * @return
     */
    public static AttendanceTypeEnum getByCode(int code) {
        for (AttendanceTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
