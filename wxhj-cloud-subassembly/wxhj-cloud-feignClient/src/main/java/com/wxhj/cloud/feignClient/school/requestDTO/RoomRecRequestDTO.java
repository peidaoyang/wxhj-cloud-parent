package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel("新增入住人员信息")
public class RoomRecRequestDTO {
    @ApiModelProperty(value = "床位号")
    private Integer number;
    @ApiModelProperty(value = "学号")
    private String otherCode;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "用户名称")
    private String accountName;
}
