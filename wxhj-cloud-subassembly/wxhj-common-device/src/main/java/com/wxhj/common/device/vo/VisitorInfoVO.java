package com.wxhj.common.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 访客信息模型
 * @author daxiong
 * @date 2020/4/22 3:17 下午
 */
@Data
@ApiModel("访客信息模型")
public class VisitorInfoVO {
	@ApiModelProperty("编号")
	private String id;
	@ApiModelProperty("身份证号")
	private String idNumber;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("访客姓名")
	private String name;
	@ApiModelProperty("被访问者id")
	private String accountId;
	@ApiModelProperty("被访问者姓名")
	private String accountName;
	@ApiModelProperty("访问者公司")
	private String company;
	@ApiModelProperty("位置(暂时不需要)")
	private String position;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("访问有效期开始时间")
	private Date beginTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("访问有效期结束时间")
	private Date endTime;
	@ApiModelProperty("访问原因")
	private String reason;
	@ApiModelProperty("组织id")
	private String organizeId;
	@ApiModelProperty("审核是否通过(0为通过)")
	private Integer isCheck;
	@ApiModelProperty("场景编号")
	private String sceneId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("记录创建时间")
	private Date creatorTime;

}
