package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author cya
 * @Date 2020/5/22
 * @Version V1.0
 **/
@Data
@ApiModel(value = "根据账户类型和账户id查询账户信息")
public class AccountByOtherCodeAndTypeRequestDTO {
    @ApiModelProperty(value = "其他编码",example = "80123")
    @NotBlank
    private String otherCode;

    @ApiModelProperty(value = "组织编号",example = "alhglahf-123123llgagh-123kag")
    @NotBlank
    private String organizeId;

    @ApiModelProperty(value = "类型列表")
    private List<Integer> typeList;
}
