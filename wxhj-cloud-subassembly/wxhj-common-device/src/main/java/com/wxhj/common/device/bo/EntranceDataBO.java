package com.wxhj.common.device.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EntranceDataBO {
    private String orderNumber;
    // 通行日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accessDate;
    // 通行时间 基于当天的分钟
    private Integer accessTime;
    // 通行编号
    private String entranceId;
    // 通行流水
    private Integer entranceSequence;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDatetime;
    // 通行人员id
    private String accountId;
    // 场景id
    private String sceneId;
    // 设备id
    private String deviceId;
    // 组织id
    private String organizeId;
    // 设备流水
    private Long serialNumber;
    // 记录时间戳(unix秒)
    private Long recordTimeStamp;
    // 人员姓名
    private String accountName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 通行规则有效期开始时间
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 通行规则有效期结束时间
    private Date endDate;
    // 设备名称
    private String deviceName;
    //体温
    private Double temperature;
}
