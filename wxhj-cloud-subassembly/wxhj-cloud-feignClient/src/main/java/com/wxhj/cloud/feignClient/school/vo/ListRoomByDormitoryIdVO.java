package com.wxhj.cloud.feignClient.school.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "根据楼栋id和楼层查询房间信息")
public class ListRoomByDormitoryIdVO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "房间号")
    private Integer number;
}
