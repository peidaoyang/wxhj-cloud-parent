package com.wxhj.cloud.feignClient.business.request;


import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "根据账户id获取出差记录列表")
public class ListOnBusinessByAccountIdRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "账户id",example = "0000057")
    private String accountId;
}
