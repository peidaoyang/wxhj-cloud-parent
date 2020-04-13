package com.wxhj.cloud.feignClient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020-04-09 17:54
 */
@ApiModel(value = "请假出差等时间段的DTO")
@Data
public class DurationDTO {

    @ApiModelProperty(value = "类型，2：请假，3：出差")
    private Integer type;

    private Integer beginTime;
    private Integer endTime;
    private String timeDesc;

    private String duration;
}
