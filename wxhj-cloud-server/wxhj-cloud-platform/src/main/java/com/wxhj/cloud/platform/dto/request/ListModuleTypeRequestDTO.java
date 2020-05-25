package com.wxhj.cloud.platform.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@ApiModel(value = "组织快选菜单请求对象")
@Data
public class ListModuleTypeRequestDTO {
    @ApiModelProperty(value = "id")
    @NotBlank
    private String id;

    @ApiModelProperty(value = "组织类型")
    @Min(-1)
    @Max(99)
    private Integer orgType;
}
