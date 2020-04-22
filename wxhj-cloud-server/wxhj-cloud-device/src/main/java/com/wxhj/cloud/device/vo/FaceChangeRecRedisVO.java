package com.wxhj.cloud.device.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@ApiModel(description = "人脸信息返回模型")
public class FaceChangeRecRedisVO {
    @ApiModelProperty(value = "唯一编号")
    private String id;
    @ApiModelProperty(value = "当前索引")
    private Long currentIndex;
    @ApiModelProperty(value = "主表id")
    private Long masterId;
    @ApiModelProperty(value = "人员id")
    private String accountId;
    @ApiModelProperty(value = "图片地址(当前字段为图片名称)")
    private String imageName;
    @ApiModelProperty(value = "操作类型(添加:0,删除:1)")
    private Integer operateType;
    @ApiModelProperty(value = "身份证号")
    private String idNumber;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;
    @ApiModelProperty(value = "图片外网访问地址")
    private String imageUrl1;

    public String getImageUrl() {
        return this.imageName;
    }

}
