package com.wxhj.cloud.feignClient.school.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/21
 * @Version V1.0
 **/
@Data
@ApiModel(value = "楼栋信息返回对象")
public class DormitoryInfoResponseDTO {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("楼栋号")
    private Integer number;
    @ApiModelProperty("楼层数")
    private Integer floorNumber;
    @ApiModelProperty("备注")
    private String note;
    @ApiModelProperty("场景编号")
    private String sceneId;
}
