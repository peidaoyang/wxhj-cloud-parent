package com.wxhj.common.device.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@ApiModel(value = "访客信息对象")
public class VisitInfoBO{
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "访客姓名")
    private String name;
    @ApiModelProperty(value = "被访者id")
    private String accountId;
    @ApiModelProperty(value = "设备编号")
    private String deviceId;
    @ApiModelProperty(value = "可访问开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    @ApiModelProperty(value = "可访问结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "访问时间")
    private LocalDateTime visitorTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "")
    private LocalDateTime recordDatetime;
    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;
    @ApiModelProperty(value = "场景编号")
    private String sceneId;
}
