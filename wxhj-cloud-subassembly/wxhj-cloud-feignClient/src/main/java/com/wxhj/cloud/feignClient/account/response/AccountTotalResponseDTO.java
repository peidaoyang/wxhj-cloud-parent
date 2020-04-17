package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "账户统计返回对象")
public class AccountTotalResponseDTO {
    @ApiModelProperty(value = "账户总数量")
    private Integer accoutTotal;
    @ApiModelProperty(value = "人脸账户总数量")
    private Integer accountFaceTotal;
    @ApiModelProperty(value = "今日门禁识别总次数")
    private Integer entranceTotal;


}
