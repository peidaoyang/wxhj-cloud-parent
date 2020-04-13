package com.wxhj.cloud.feignClient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-09 17:54
 */
@ApiModel(value = "请假出差等时间段的DTO")
@Data
public class DurationDTO {

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "类型，2：请假，3：出差")
    private Integer type;

    private Integer beginTime;
    private Integer endTime;
    private String timeDesc;

    private String duration;
}
