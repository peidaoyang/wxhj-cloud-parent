package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ToString
@ApiModel(description = "人员修改权限组 请求对象")
public class SubmitAccountAuthorityRequestDTO {
    @ApiModelProperty(value = "用户id", example = "0000000008")
    @NotBlank(message = "不能为空")
    private String accountId;
    @ApiModelProperty(value = "用户权限组idlist")
    private List<String> authorityGroupIdList;
}
