package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author daxiong
 * @date 2020-04-07 15:43
 */
@ApiModel(value = "出差VO")
@Data
public class OnBusinessVO {
    private String id;
    private String accountId;
    private String accountName;
    private String organizeId;
    @ApiModelProperty(value = "出差时长")
    private String duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核状态", example = "1：未审核，2：审核成功，3：审核失败")
    private Integer status;
    @ApiModelProperty(value = "审核状态中文描述", example = "审核中")
    private String statusName;
    @ApiModelProperty(value = "审核时间")
    private LocalDateTime approveTime;
    @ApiModelProperty(value = "出差原因")
    private String reason;
    private String memo;
}
