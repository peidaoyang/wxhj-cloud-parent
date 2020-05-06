package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "设置状态查询 请求状态")
public class ListDeviceStateRequestDTO extends CommonListPageRequestDTO {
	@ApiModelProperty(value = "设备状态：0,全部；1,在线;2,离线 ")
    @Min(0)
    @Max(5)
    private Integer type;
}
