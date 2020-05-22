package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cya
 * @Date 2020/5/21
 * @Version V1.0
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "根据权限组id删除对应的人员 请求对象")
public class DeleteByAccountIdAndAuthorityIdRequestDTO {
    @ApiModelProperty(value = "权限组编号")
    private String authorityId;
    @ApiModelProperty(value = "账户id",example = "000077")
    private String accountId;
}
