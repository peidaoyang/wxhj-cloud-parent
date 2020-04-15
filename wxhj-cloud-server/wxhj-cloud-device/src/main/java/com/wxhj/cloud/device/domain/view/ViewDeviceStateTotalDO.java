package com.wxhj.cloud.device.domain.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "view_device_state_total")
public class ViewDeviceStateTotalDO {
    @Id
    @ApiModelProperty(value = "组织id")
    private String organizeId;

    @ApiModelProperty(value = "设备数量统计")
    private Integer deviceCount;
    @ApiModelProperty(value = "设备类型")
    private Integer deviceType;
}
