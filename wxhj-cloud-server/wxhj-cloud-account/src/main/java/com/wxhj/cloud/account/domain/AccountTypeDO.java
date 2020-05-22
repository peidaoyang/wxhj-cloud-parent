package com.wxhj.cloud.account.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Data
@Table(name = "account_type")
public class AccountTypeDO {
    @ApiModelProperty(value = "人员类型")
    @Id
    private Integer type;
    @ApiModelProperty(value = "人员类型名称")
    private String name;
    @ApiModelProperty(value = "组织类型：|隔开")
    private String orgType;
}
