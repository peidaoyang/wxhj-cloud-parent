package com.wxhj.cloud.feignClient.device.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description="设备状态统计")
public class DeviceStateTotalVO {
    @ApiModelProperty(value = "总设备数（不能排序）")
    private Integer total;
    @ApiModelProperty(value = "在线设备数（不能排序）")
    private Integer onLineTotal;
    @ApiModelProperty(value = "离线设备数（不能排序）")
    private Integer outLineTotal;
}
