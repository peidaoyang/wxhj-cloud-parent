package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AutoSychroType {
    AUTO(0, "不自动同步"),
    ISAUTO(1, "自动同步");
    private Integer code;
    private String msg;
}
