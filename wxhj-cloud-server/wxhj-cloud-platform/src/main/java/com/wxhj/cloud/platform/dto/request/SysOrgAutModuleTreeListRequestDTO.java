package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Data
@ApiModel(value = "新增组织全部菜单列表 请求对象")
public class SysOrgAutModuleTreeListRequestDTO {
    @ApiModelProperty(value = "id")
    @NotBlank
    private String id;

    @ApiModelProperty(value = "组织类型")
    @Min(-1)
    @Max(99)
    private Integer orgType;
}
