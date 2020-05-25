package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-09 17:01
 */
@AllArgsConstructor
@Getter
public enum LegalVocationTypeEnum {
    // 正常工作日
    NORMAL_ON_WORK(0, "正常工作日"),
    // 正常休息日
    NORMAL_OFF_WORK(1, "正常休息日"),
    // 国家法定调休日
    LEGAL_ON_WORK(2, "国家法定调休日"),
    // 国家法定节假日
    LEGAL_OFF_WORK(3, "国家法定节假日"),
    // 特殊工作日1
    SPECIAL_WORK_1(4, "自定义工作日1"),
    // 特殊工作日2
    SPECIAL_WORK_2(5, "自定义工作日2"),
    // 特殊工作日3
    SPECIAL_WORK_3(6, "自定义工作日3"),
    // 特殊工作日4
    SPECIAL_WORK_4(7, "自定义工作日4"),


    UNKNOWN(404, "未知枚举"),
    ;

    private int code;
    private String desc;

    /**
     * 根据code值获取对应的枚举
     * @param code
     * @return
     */
    public static LegalVocationTypeEnum getByCode(int code) {
        for (LegalVocationTypeEnum item : values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return UNKNOWN;
    }
}
