package com.wxhj.common.device.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 通用仅包含id的请求
 *
 * @author daxiong
 * @date 2020/4/22 3:20 下午
 */
@ApiModel(value = "通用仅包含id的请求")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceCommonIdRequestDTO {
    @ApiModelProperty(value = "id")
    @NotBlank
    private String id;
}
