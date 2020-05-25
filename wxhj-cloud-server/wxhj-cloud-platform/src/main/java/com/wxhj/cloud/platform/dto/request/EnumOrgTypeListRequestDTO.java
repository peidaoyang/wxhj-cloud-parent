package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Data
@ApiModel("组织类型枚举 请求对象")
public class EnumOrgTypeListRequestDTO {
    @ApiModelProperty(value = "组织类型")
    @Min(-1)
    @Max(20)
    private Integer orgType;
}
