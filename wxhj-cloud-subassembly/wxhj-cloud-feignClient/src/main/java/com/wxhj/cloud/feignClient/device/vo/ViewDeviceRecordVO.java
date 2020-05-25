package com.wxhj.cloud.feignClient.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "一周内设备记录统计")
@Data
public class ViewDeviceRecordVO {
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    @ApiModelProperty(value = "设备类型")
    private Integer recordType;
    @ApiModelProperty(value = "记录类型备注")
    private String recordTypeStr;
    @ApiModelProperty(value = "记录数量")
    private Integer receivedCount;
    @ApiModelProperty(value = "记录日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate receivedDate;
}
