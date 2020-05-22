package com.wxhj.cloud.feignClient.school.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "添加入住人员失败返回对象")
public class InsertRoomRecVO {
    @ApiModelProperty(value = "学号")
    private String otherCode;
    @ApiModelProperty(value = "账户名字")
    private String accountName;
    @ApiModelProperty(value = "失败原因")
    private String errorMessage;
}
