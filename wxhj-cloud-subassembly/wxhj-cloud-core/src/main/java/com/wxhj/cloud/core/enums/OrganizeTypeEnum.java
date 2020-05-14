package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OrganizeTypeEnum {
    //默认类型
    DEFAULT_TYPE(0,"默认"),
    SCHOOL_TYPE(1,"校园"),
    UNKNOWN(404, "unKnown");
    private int code;
    private String msg;

    /**
     * 根据code获得对应的枚举
     * @param code
     * @return
     */
    public static OrganizeTypeEnum getCode(int code){
        for(OrganizeTypeEnum item: values()){
            if(item.getCode() == code){return item;}
        }
        return UNKNOWN;
    }
}
