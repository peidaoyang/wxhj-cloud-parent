package com.wxhj.cloud.feignClient.device.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description="设备类型统计")
public class DeviceTypeTotalVO {
    @ApiModelProperty(value = "组织id")
    private String organizeId;

    @ApiModelProperty(value = "设备数量统计")
    private Integer deviceCount;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;

    @ApiModelProperty(value = "设备类型解释")
    private String deviceTypeStr;
}
