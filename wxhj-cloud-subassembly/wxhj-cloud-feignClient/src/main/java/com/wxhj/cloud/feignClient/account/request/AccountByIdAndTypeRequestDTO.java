package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/22
 * @Version V1.0
 **/
@Data
@ApiModel(value = "根据账户类型和账户id查询账户信息")
public class AccountByIdAndTypeRequestDTO {
    @ApiModelProperty(value = "账户id",example = "0000091")
    private String accoutId;

    @ApiModelProperty(value = "类型列表")
    private List<Integer> typeList;
}
