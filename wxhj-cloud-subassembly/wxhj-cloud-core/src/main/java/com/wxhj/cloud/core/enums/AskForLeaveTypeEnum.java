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
    // 年假
    ANNUAL_LEAVE(10, "年假"),
    // 事假
    PERSONAL_LEAVE(20, "事假"),
    // 病假
    SICK_LEAVE(30, "病假"),
    // 调休
    EXCHANGE_LEAVE(40, "调休"),
    // 其他
    MATERNITY_LEAVE(50, "其他"),

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

    public static void main(String[] args) {
        for (AskForLeaveTypeEnum item : values()) {
            System.out.println(item.getCode());
        }
    }

}
