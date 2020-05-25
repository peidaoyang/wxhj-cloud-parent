package com.wxhj.cloud.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("组织类型枚举列表 返回对象")
public class EnumOrgTypeListVO {
    @ApiModelProperty(value="枚举类型编号")
    private Integer enumType;
    @ApiModelProperty(value="枚举类型名称")
    private String enumTypeName;
}
