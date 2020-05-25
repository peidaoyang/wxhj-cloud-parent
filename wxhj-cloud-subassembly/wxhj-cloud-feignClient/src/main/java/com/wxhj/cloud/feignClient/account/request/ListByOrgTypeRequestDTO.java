package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("根据组织类型获取人员类型列表 请求对象")
public class ListByOrgTypeRequestDTO {
    @ApiModelProperty(value = "组织类型,默认为0，校园版为1", example = "0")
    @Min(0)
    @Max(20)
    private Integer orgType;
}
