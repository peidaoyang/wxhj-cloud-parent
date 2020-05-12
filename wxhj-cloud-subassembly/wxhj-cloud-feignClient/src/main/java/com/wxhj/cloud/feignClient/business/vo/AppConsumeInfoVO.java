package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

@Data
@ApiModel("消费记录查询返回对象")
public class AppConsumeInfoVO implements IOrganizeModel {
    @ApiModelProperty(value = "消费时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date consumeDate;
    @ApiModelProperty(value = "组织名称（不能排序）")
    private String organizeName;
    @ApiModelProperty(value = "组织Id")
    private String organizeId;
    @ApiModelProperty(value = "消费金额（单位元）")
    private Double consumeMoney;

    public void setConsumeMoney(Double consumeMoney) {
        this.consumeMoney = Optional.of(consumeMoney).orElse(0.0) / 100.00;
    }
}
