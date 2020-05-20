package com.wxhj.cloud.core.interfaces;


import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IDeviceRecord {
    LocalDateTime getRecordDatetime();

    void setRecordDatetime(LocalDateTime recordDatetime);

    //设备号_设备流水号_时间戳
    String getOrderNumber();

    void setOrderNumber(String orderNumber);

    //设备流水号
    Long getSerialNumber();

    void setSerialNumber(Long serialNumber);

    //用户id
    String getAccountId();

    void setAccountId(String accountId);

    //场景id
    String getSceneId();

    void setSceneId(String sceneId);

    //设备id
    String getDeviceId();

    void setDeviceId(String deviceId);

    //组织id
    String getOrganizeId();

    void setOrganizeId(String organizeId);
}