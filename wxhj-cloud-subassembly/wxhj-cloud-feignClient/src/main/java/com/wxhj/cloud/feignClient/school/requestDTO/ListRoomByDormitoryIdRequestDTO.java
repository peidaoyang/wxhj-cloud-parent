package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "根据楼栋id和楼层查询房间数量")
public class ListRoomByDormitoryIdRequestDTO {
    @ApiModelProperty(value = "楼栋id")
    @NotBlank
    private String dormitoryId;

    @ApiModelProperty(value = "楼层")
    @Min(0)
    @Max(99)
    private Integer floorNumber;

    @ApiModelProperty(value = "宿舍类型(0：男生宿舍,1：女生宿舍,2：教职工宿舍)")
    @Min(0)
    @Max(99)
    private Integer type;
}
