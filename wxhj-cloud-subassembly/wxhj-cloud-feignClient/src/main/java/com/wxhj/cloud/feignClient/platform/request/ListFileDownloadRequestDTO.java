package com.wxhj.cloud.feignClient.platform.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author daxiong
 * @date 2020-04-03 15:18
 */
@Data
@Api(value = "文件下载请求实体")
public class ListFileDownloadRequestDTO implements IPageRequestModel {
    @ApiModelProperty("组织id")
    @NotBlank
    private String organizeId;
    private String sceneId;

    @ApiModelProperty("搜索条件，创建时间-起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("搜索条件，创建时间-结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "单页行数", example = "50")
    @Min(1)
    private Integer rows;
    @ApiModelProperty(value = "当前页数", example = "1")
    @Min(1)
    private Integer page;
    @ApiModelProperty(value = "排序字段", example = "id")
    @NotNull
    private String orderBy;
}