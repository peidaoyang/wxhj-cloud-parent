package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-07 13:51
 */
@Getter
@AllArgsConstructor
public enum AskForLeaveTypeEnum {
    // 事假
    PERSONAL_LEAVE(1, "事假"),
    // 病假
    SICK_LEAVE(2, "病假"),
    // 婚假
    MARRIAGE_LEAVE(3, "婚假"),
    // 丧假
    FUNERAL_LEAVE(4, "丧假"),
    // 产假
    MATERNITY_LEAVE(5, "产假"),

    // UNKNONW
    UNKNOWN(404, "unKnown"),
    ;

    private int code;
    private String desc;

    /**
     * 根据code获取对应枚举
     * @param code
     * @return
     */
    public static AskForLeaveTypeEnum getByCode(int code) {
        for (AskForLeaveTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }

}
