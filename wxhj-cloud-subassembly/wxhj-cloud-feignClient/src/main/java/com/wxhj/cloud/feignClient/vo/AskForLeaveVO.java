package com.wxhj.cloud.feignClient.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-07 15:43
 */
@ApiModel(value = "请假")
@Data
public class AskForLeaveVO {
    private String accountId;
    private String accountName;
    private String organizeId;
    @ApiModelProperty(value = "请假类型", example = "1")
    private Integer type;
    @ApiModelProperty(value = "请假类型中文描述", example = "事假")
    private String typeName;
    @ApiModelProperty(value = "请假时长")
    private String duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "审核状态", example = "1")
    private Integer status;
    @ApiModelProperty(value = "审核状态中文描述", example = "审核中")
    private String statusName;
    @ApiModelProperty(value = "审核时间")
    private Date approveTime;
    @ApiModelProperty(value = "请假原因")
    private String reason;
    private String memo;
}