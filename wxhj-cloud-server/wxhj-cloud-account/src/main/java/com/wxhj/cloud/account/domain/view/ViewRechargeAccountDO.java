package com.wxhj.cloud.account.domain.view;



import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "view_recharge_account")
public class ViewRechargeAccountDO {
	@ApiModelProperty(value = "充值流水号")
	private String id;
	@ApiModelProperty(value = "账户编号")
	private String accountId;
	@ApiModelProperty(value = "充值金额")
	private Integer amount;
	@ApiModelProperty(value = "手续费")
	private Integer serviceAmount;
	@ApiModelProperty(value = "充值方类型")
	private Integer type;
	@ApiModelProperty(value = "充值类型")
	private Integer payType;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creatorTime;
	@ApiModelProperty(value ="用户名")
	private String name;
	@ApiModelProperty(value ="组织编号")
	private String organizeId;
}
