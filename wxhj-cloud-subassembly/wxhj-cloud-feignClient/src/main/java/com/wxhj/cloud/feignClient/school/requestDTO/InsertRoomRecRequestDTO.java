package com.wxhj.cloud.feignClient.school.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "新增入住信息")
public class InsertRoomRecRequestDTO {
//    @ApiModelProperty("主键")
//    private String id;
    @ApiModelProperty(value = "房间主键")
    @NotBlank
    private String roomId;

    @ApiModelProperty(value = "组织编号")
    @NotBlank
    private String organizeId;

    @ApiModelProperty(value = "楼栋主键")
    @NotBlank
    private String dormitoryId;

    @ApiModelProperty(value = "人员相关信息")
    List<RoomRecRequestDTO>  roomRecList;
}
