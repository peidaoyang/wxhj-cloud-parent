package com.wxhj.common.device.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author daxiong
 * @date 2020/5/7 11:18 上午
 */
@Data
@ApiModel("人脸下发异常上送DTO")
public class DeviceFaceExDTO {
    @ApiModelProperty("场景id")
    private String sceneId;

    @ApiModelProperty("当前场景下的流水")
    private Long currentIndex;

    @ApiModelProperty("组织id")
    private String organizeId;

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
    private LocalDateTime createTime;

}

