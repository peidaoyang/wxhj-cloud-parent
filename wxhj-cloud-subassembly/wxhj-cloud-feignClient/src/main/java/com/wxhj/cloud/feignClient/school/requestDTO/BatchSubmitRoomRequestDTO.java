package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Data
@ApiModel(value = "批量添加房间 请求对象")
public class BatchSubmitRoomRequestDTO {
    @ApiModelProperty(value = "楼栋编号")
    @NotBlank
    private String dormitoryId;
    @ApiModelProperty(value = "楼栋号")
    @Min(0)
    private Integer dormitoryNumber;
    @ApiModelProperty(value = "最小楼层")
    @Min(0)
    @Max(99)
    private Integer minFloor;
    @ApiModelProperty(value = "最大楼层")
    @Min(0)
    @Max(99)
    private Integer maxFloor;
    @ApiModelProperty(value = "每层房间数量")
    @Min(0)
    @Max(99)
    private Integer roomSize;
    @ApiModelProperty(value = "宿舍类型")
    @Min(0)
    @Max(20)
    private Integer type;
    @ApiModelProperty(value = "床位数")
    @Min(0)
    @Max(50)
    private Integer bedNumber;
    @ApiModelProperty(value = "组织编号")
    @NotBlank
    private String organizeId;
}
