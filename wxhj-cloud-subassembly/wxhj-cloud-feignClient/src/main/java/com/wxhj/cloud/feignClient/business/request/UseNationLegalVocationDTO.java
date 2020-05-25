package com.wxhj.cloud.feignClient.business.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author daxiong
 * @date 2020-04-08 17:03
 */
@Data
@ApiModel(value = "应用国家法定节假日DTO")
public class UseNationLegalVocationDTO {

    @ApiModelProperty(value = "开始时间", example = "2020-04-10")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private LocalDate beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-04-10")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull
    private LocalDate endTime;
}
