package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "修改入住人员信息")
public class UpdateRoomRecRequestDTO {
    @ApiModelProperty(value = "id")
    @NotBlank
    private String id;
    @ApiModelProperty(value = "房间主键")
    @NotBlank
    private String roomId;
    @ApiModelProperty(value = "床位号")
    @Min(0)
    private Integer number;

    @ApiModelProperty(value = "用户名称")
    @NotBlank
    private String accountName;
    @ApiModelProperty(value = "账户id")
    @NotBlank
    private String accountId;
    @ApiModelProperty(value = "学号")
    private String otherCode;
}
