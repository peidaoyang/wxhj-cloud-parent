package com.wxhj.cloud.feignClient.school.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "根据组织id查询楼栋列表")
public class ListDormitoryByOrgIdVO {
    @ApiModelProperty("楼栋信息主键")
    private String id;
    @ApiModelProperty("楼栋号")
    private Integer number;
    @ApiModelProperty("楼层数")
    private Integer floorNumber;
}
