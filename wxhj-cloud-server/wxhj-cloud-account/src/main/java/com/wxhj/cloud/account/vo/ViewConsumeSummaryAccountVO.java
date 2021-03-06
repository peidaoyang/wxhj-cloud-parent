package com.wxhj.cloud.account.vo;



import javax.persistence.Id;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import java.time.LocalDateTime;

@Data
@ApiModel(value="个人消费汇总返回 对象")
public class ViewConsumeSummaryAccountVO implements IOrganizeModel{
	@Id
	@ApiModelProperty(value = "账户id")
	private String accountId;
	@Id
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	@ApiModelProperty(value = "汇总日期")
	private LocalDateTime consumeDate;
	@ApiModelProperty(value = "汇总金额")
	private Double consumeMoney;
	@ApiModelProperty(value = "组织id")
	private String organizeId;
	@ApiModelProperty(value = "用户名")
	private String name;
	@ApiModelProperty(value = "组织名称")
	private String organizeName;

	public void setConsumeMoney(Double consumeMoney) {
		this.consumeMoney = consumeMoney/100.00;
	}
}
