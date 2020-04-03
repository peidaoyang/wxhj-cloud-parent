package com.wxhj.cloud.core.enums;

import com.wxhj.cloud.core.statics.BusinessStaticClass;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件下载类型的枚举
 * @author daxiong
 * @date 2020-04-02 14:41
 */
@Getter
@AllArgsConstructor
public enum FileDownloadTypeEnum {

    // 账户信息下载
    ACCOUNT_DOWNLOAD(1001, BusinessStaticClass.ACCOUNT),


    ;

    private int code;
    private String type;
}
