/**
 * @fileName: DeviceInitializeRequestDTO.java
 * @author: pjf
 * @date: 2020年3月3日 下午1:29:04
 */

package com.wxhj.common.device.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @className DeviceInitializeRequestDTO.java
 * @author pjf
 * @date 2020年3月3日 下午1:29:04   
 */

/**
 * @className DeviceInitializeRequestDTO.java
 * @author pjf
 * @date 2020年3月3日 下午1:29:04 
 */
@ApiModel(value = "设备初始化请求对象")
@Data
public class DeviceInitializeRequestDTO {

    @ApiModelProperty(value = "设备唯一流水号")
    //@NotNull
    private String id;
    @ApiModelProperty(value = "设备唯一流水号", example = "82012345")
    @NotNull
    private String deviceId;
    @ApiModelProperty(value = "imei号")
    @NotNull
    private String imei;
    @ApiModelProperty(value = "设备型号")
    @NotNull
    private String deviceModel;
    @ApiModelProperty(value = "设备类型")
    @Min(0)
    @Max(99)
    private Integer deviceType;
}
