package com.wxhj.cloud.feignClient.account.request.consume.rule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author daxiong
 * @date 2020/5/28 10:39 上午
 */
@Data
@ApiModel("保存消费规则DTO")
public class SubmitConsumeRuleDTO {

	@ApiModelProperty("主键id")
	private String id;
	@ApiModelProperty("消费规则名称")
	@NotBlank
	private String name;
	@ApiModelProperty("时间段描述")
	@NotBlank
	private String timeDesc;
	@ApiModelProperty("第一时间段开始分钟数")
	@NotNull
	private Integer beginTime1;
	@ApiModelProperty("第一时间段结束分钟数")
	@NotNull
	private Integer endTime1;
	@ApiModelProperty("第二时间段开始分钟数")
	private Integer beginTime2;
	@ApiModelProperty("第二时间段结束分钟数")
	private Integer endTime2;
	@ApiModelProperty("第三时间段开始分钟数")
	private Integer beginTime3;
	@ApiModelProperty("第三时间段结束分钟数")
	private Integer endTime3;
	@ApiModelProperty("规则类型。0：普通消费，1：定次消费")
	@NotNull
	private Integer ruleType;
	@ApiModelProperty("次数下限")
	private Integer countFloor;
	@ApiModelProperty("次数上限")
	@NotNull
	private Integer countCeiling;
	@ApiModelProperty("基准消费金额，以分为单位")
	private Integer baseAmount;
	@ApiModelProperty("折扣类型。0：减免金额，1：折扣率")
	private Integer discountType;
	@ApiModelProperty("折扣值")
	private Integer discountValue;
	@ApiModelProperty("根组织id")
	@NotBlank
	private String organizeId;
	@ApiModelProperty("备注")
	private String memo;
	@ApiModelProperty("场景id")
	private List<String> sceneIdList;
}
