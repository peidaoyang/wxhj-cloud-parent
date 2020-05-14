package com.wxhj.cloud.platform.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/12
 * @Version V1.0
 **/
@Table(name = "sys_organize_authorize_type")
@Data
@ApiModel("组织类型菜单权限表")
public class SysOrganizeAuthorizeTypeDO {
    @ApiModelProperty(value = "菜单ID")
    private String moduleId;
    @ApiModelProperty(value = "组织类型")
    private Integer type;
}
