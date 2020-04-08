package com.wxhj.cloud.feignClient.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取请假记录列表请求DTO
 * @author daxiong
 * @date 2020-04-07 15:14
 */
@Data
@ApiModel(value = "获取请假记录列表请求")
public class ListAskForLeaveRequestDTO extends CommonListPageRequestDTO {

    /**
     * 请假状态
     * @see com.wxhj.cloud.core.enums.AskForLeaveTypeEnum
     */
    @ApiModelProperty(value = "审核状态")
    private Integer status;

}
