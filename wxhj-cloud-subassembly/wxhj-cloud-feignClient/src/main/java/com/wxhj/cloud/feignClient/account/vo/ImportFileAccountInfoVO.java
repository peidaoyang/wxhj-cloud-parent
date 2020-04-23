package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "导入失败账户 返回对象")
@Data
public class ImportFileAccountInfoVO {
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "失败原因")
    private String reason;
}
