package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/22
 * @Version V1.0
 **/
@Data
@ApiModel("根据账户类型和账户id查询账户信息 返回对象")
public class AccountByIdAndTypeVO {
    @ApiModelProperty("用户id")
    private String accountId;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("学号")
    private String otherCode;
    @ApiModelProperty("手机号")
    private String phoneNumber;
    @ApiModelProperty("身份证")
    private String idNumber;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("账户类型")
    private Integer accountType;
}
