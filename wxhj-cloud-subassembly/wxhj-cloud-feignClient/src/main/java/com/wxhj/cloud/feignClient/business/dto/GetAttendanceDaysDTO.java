package com.wxhj.cloud.feignClient.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * @author daxiong
 * @date 2020-04-08 17:03
 */
@Data
@ApiModel(value = "获取考勤规则DTO")
public class GetAttendanceDaysDTO {

    @ApiModelProperty(value = "账户id")
    private String accountId;

    @ApiModelProperty(value = "开始时间", example = "2020-04-10 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-04-10 12:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endTime;
}
