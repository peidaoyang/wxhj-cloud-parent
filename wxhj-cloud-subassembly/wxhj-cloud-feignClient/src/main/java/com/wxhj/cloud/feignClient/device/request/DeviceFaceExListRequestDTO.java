package com.wxhj.cloud.feignClient.device.request;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/5/8 10:16 上午
 */
@Data
@ApiModel(value = "设备人脸异常上送列表DTO")
public class DeviceFaceExListRequestDTO extends CommonListPageRequestDTO {
    @ApiModelProperty("场景id")
    private String sceneId;
}
