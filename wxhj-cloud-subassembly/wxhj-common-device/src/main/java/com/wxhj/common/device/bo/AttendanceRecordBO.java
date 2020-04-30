package com.wxhj.common.device.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class AttendanceRecordBO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录上送时间")
    private Date recordDatetime;
    @ApiModelProperty(value = "设备订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;
    @ApiModelProperty(value = "用户id")
    private String accountId;
    @ApiModelProperty(value = "场景id")
    private String sceneId;
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
}
