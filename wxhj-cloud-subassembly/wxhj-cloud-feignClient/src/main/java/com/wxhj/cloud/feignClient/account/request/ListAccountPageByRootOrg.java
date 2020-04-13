package com.wxhj.cloud.feignClient.account.request;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "分页根组织账户信息（根组织）")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListAccountPageByRootOrg extends CommonListPageRequestDTO {
    @ApiModelProperty(value = "请求类型",example = "手机号：phoneNumber，姓名：name，其他编码：otherCode")
    private String type;
}
