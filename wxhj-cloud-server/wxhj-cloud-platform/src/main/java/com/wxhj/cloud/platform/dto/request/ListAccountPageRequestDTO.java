package com.wxhj.cloud.platform.dto.request;

import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ToString
@ApiModel( description = "账户及子账户查询请求对象")
public class ListAccountPageRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
    @NotEmpty
    private String organizeId;

    @ApiModelProperty(value = "请求类型",example = "手机号：phoneNumber，姓名：name，其他编码：otherCode")
    private String type;
}
