package com.wxhj.cloud.feignClient.device.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="设备类型统计")
public class DeviceTypeTotalResponseDTO {
    @ApiModelProperty(value = "组织id")
    private String organizeId;

    @ApiModelProperty(value = "设备数量统计")
    private Integer deviceCount;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
}
