package com.wxhj.cloud.feignClient.platform.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@ApiModel(value = "公告信息返回对象")
@Data
public class AnnouncementListVO implements IOrganizeModel {
    @ApiModelProperty(value = "编号")
    private String id;
    @ApiModelProperty(value = "内容简介")
    private String content;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "组织主键")
    private String organizeId;
    @ApiModelProperty(value = "组织名称")
    private String organizeName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatorTime;
    @ApiModelProperty(value = "创建人id")
    private String creatorUserId;
}
