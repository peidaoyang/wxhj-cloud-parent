package com.wxhj.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daxiong
 * @date 2020-04-02 14:46
 */
@Getter
@AllArgsConstructor
public enum FileDownloadStatusEnum {
    // 下载中
    RUNNING(1, "下载中"),
    // 下载成功
    SUCCEED(2, "下载成功"),
    // 下载失败
    FAILED(3, "下载失败"),
    // 已取消
    CANCELED(4, "取消下载"),

    ;

    private int code;
    private String status;
}
