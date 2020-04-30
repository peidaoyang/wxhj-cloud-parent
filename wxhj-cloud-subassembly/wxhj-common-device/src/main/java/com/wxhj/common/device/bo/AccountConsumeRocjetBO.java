package com.wxhj.common.device.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "账户消费信息 对象")
public class AccountConsumeRocjetBO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "消费时间")
    private Date consumeDate;
    @ApiModelProperty(value = "订单号")
    private String orderNumber;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "消费金额(分)")
    private Integer consumeMoney;
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    @ApiModelProperty(value = "场景id")
    private String sceneId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录上送时间")
    private Date recordDatetime;
    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;

}