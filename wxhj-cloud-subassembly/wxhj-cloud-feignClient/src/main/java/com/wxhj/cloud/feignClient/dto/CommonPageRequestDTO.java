package com.wxhj.cloud.feignClient.dto;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.statics.CaseFormatStaticClass;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@ToString
@ApiModel(value = "通用的分页查询")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonPageRequestDTO implements IPageRequestModel {
    @ApiModelProperty(value = "查询内容", example = "")
    protected String nameValue;
    @ApiModelProperty(value = "单页行数", example = "50")
    @Min(1)
    protected Integer rows;
    @ApiModelProperty(value = "当前页数", example = "1")
    @Min(1)
    protected Integer page;
    @ApiModelProperty(value = "排序字段")
    @NotBlank(message = "不能为空")
    protected String orderBy;

    @Override
    public void setOrderBy(String orderBy) {
        this.orderBy =
                CaseFormatStaticClass.CAMEL_TO_UNDERSCORE.convert(orderBy);
        //HumpUtil.humpToLine(orderBy)
        //;
    }
}
