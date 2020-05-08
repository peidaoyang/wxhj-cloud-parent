package com.wxhj.cloud.feignClient.business.request;


import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@ApiModel(value = "根据账户id获取出差记录列表")
public class ListOnBusinessByAccountIdRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "账户id",example = "0000057")
    private String accountId;

    @ApiModelProperty(value = "审核状态 1：未审核，2：审核成功，3：审核失败",example = "0")
    @Min(0)
    private Integer isCheck;
}
