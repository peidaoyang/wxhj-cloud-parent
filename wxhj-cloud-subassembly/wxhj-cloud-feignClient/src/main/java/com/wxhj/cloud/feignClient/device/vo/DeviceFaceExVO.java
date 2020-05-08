package com.wxhj.cloud.feignClient.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020/5/7 3:07 下午
 */
@Data
@ApiModel("设备人脸异常返回VO")
public class DeviceFaceExVO implements IOrganizeSceneModel {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("场景id")
    private String sceneId;

    @ApiModelProperty("场景名称")
    private String sceneName;

    @ApiModelProperty("当前场景下的流水")
    private Long currentIndex;

    @ApiModelProperty("组织id")
    private String organizeId;

    @ApiModelProperty("组织名称")
    private String organizeName;

    @ApiModelProperty("设备编号")
    private String deviceNo;

    @ApiModelProperty("变更唯一流水")
    private Long masterId;

    @ApiModelProperty("用户id")
    private String accountId;

    @ApiModelProperty("图片url")
    private String imageName;

    @ApiModelProperty("操作标志")
    private Integer operateType;

    @ApiModelProperty("身份证号")
    private String idNumber;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("卡号")
    private String cardNumber;

    @ApiModelProperty("异常原因")
    private String exceptionReason;

    @ApiModelProperty("状态：1：失败；2：忽略；3：已重发")
    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", example = "2020-05-07 13:50:00")
    private Date createTime;
}
