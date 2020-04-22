package com.wxhj.cloud.feignClient.business.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "已注册人脸和根组织查询账户请求对象")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListAccountByChildOrganizeVO implements IOrganizeModel {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("名称")
    private String value;
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("组织名称")
    private String organizeName;
    @ApiModelProperty("名称和组织名称拼接")
    private String valueOrg;

    public ListAccountByChildOrganizeVO(String id, String value, String organizeId) {
        this.id = id;
        this.value = value;
        this.organizeId = organizeId;
    }
}
