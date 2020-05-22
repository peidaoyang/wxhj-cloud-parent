package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@ApiModel(value = "新增房间 请求对象")
@Data
public class SubmitRoomRequestDTO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "房间号")
    @Min(0)
    private Integer number;
    @ApiModelProperty(value = "床位数")
    @Min(0)
    @Max(50)
    private Integer bedNumber;
    @ApiModelProperty(value = "楼层")
    @Min(0)
    @Max(99)
    private Integer floorNumber;
    @ApiModelProperty(value = "宿舍类型(0：男生宿舍,1：女生宿舍,2：教职工宿舍)")
    @Min(0)
    @Max(20)
    private Integer type;
    @ApiModelProperty(value = "楼栋编号")
    @NotBlank
    private String dormitoryId;
    @ApiModelProperty(value = "组织编号")
    @NotBlank
    private String organizeId;
}
