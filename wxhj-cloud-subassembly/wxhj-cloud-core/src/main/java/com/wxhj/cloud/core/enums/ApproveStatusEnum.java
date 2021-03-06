package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 * @author daxiong
 * @date 2020-04-07 14:24
 */
@Getter
@AllArgsConstructor
public enum ApproveStatusEnum {
    // 审核中
    APPROVING(1, "未审核"),
    // 审核通过
    APPROVE_SUCCESS(2, "审核成功"),
    // 审核不通过
    APPROVE_REJECTED(3, "审核失败"),




    // 未知枚举
    UNKNOWN(404, "unknown"),

    ;

    private int code;
    private String desc;

    public static ApproveStatusEnum getByCode(int code) {
        for (ApproveStatusEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
