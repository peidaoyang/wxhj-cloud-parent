package com.wxhj.common.device.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@ApiModel("班车信息对象")
public class RideInfoBO {
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "实际扣款金额(单位分)")
    private Integer amount;
    @ApiModelProperty(value = "票价")
    private Integer price;
    @ApiModelProperty(value = "用户id")
    private String accountId;
    @ApiModelProperty(value = "用户姓名")
    private String accountName;
    @ApiModelProperty(value = "班次id")
    private String flightId;
    @ApiModelProperty(value = "线路id")
    private String routeNumber;
    @ApiModelProperty(value = "车号")
    private String carNumber;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "乘车时间")
    private LocalDateTime rideTime;
    @ApiModelProperty(value = "线路名称")
    private String routeName;
    @ApiModelProperty(value = "开始站点")
    private String startSite;
    @ApiModelProperty(value = "结束站点")
    private String endSite;
    @ApiModelProperty(value = "途径站点")
    private String channelSite;
    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;
    @ApiModelProperty(value = "场景编号")
    private String sceneId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录上送时间")
    private LocalDateTime recordDatetime;

}