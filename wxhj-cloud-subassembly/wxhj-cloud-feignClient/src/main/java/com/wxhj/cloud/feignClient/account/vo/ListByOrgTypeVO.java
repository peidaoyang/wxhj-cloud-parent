package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Data
@ApiModel("根据组织类型获取人员类型列表 返回对象")
public class ListByOrgTypeVO {
    @ApiModelProperty(value = "人员类型")
    private Integer type;
    @ApiModelProperty(value = "人员类型名称")
    private String name;
}
