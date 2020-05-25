package com.wxhj.cloud.feignClient.account.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
@ToString
@ApiModel(description = "个人查询充值信息 请求对象")
public class PersonRechargeRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "账户id",example = "0000000028")
    @NotBlank
    private String accountId;
    @ApiModelProperty(value = "开始时间", example = "2019-06-11")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;
    @ApiModelProperty(value = "结束时间", example = "2020-06-11")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    @ApiModelProperty(value = "卡类型", example = "0")
    private Integer cardType;

    public void format() {
        // 兼容
        if (this.cardType == null) {
            cardType = 0;
        }
    }
}
