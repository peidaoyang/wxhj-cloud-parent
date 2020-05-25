package com.wxhj.cloud.feignClient.school;

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
@Data
@ApiModel(value = "新增楼栋")
public class SubmitDormitoryRequestDTO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "组织编号")
    @NotBlank
    private String organizeId;
    @ApiModelProperty(value = "楼栋号")
    @Min(0)
    private Integer buildingNumber;
    @ApiModelProperty(value = "楼层数")
    @Min(0)
    private Integer floor;
    @ApiModelProperty(value = "房间数量")
    private Integer room;
    @ApiModelProperty(value = "床位数量")
    private Integer bedNumber;
    @ApiModelProperty(value = "居住人数")
    private Integer resideNumber;
    @ApiModelProperty(value = "空床位数")
    private Integer emptyBedNumber;
}
