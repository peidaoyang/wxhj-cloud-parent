package com.wxhj.cloud.app.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@ApiModel(value = "出差DTO")
public class OnBusinessRequetDTO {
    @ApiModelProperty(value = "id")
    private String id;
    @NotBlank
    @ApiModelProperty(value = "用户id")
    private String accountId;

    @ApiModelProperty(value = "组织id")
    @NotBlank
    private String organizeId;

    @ApiModelProperty(value = "出差开始时间")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "出差结束时间")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "出差时长")
    private String duration;

    @ApiModelProperty(value = "出差原因")
    @NotBlank
    private String reason;
}
