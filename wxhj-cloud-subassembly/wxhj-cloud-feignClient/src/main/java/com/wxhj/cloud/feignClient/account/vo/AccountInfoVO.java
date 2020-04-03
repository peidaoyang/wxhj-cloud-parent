/** 
 * @fileName: AccountInfoVO.java  
 * @author: pjf
 * @date: 2020年2月12日 下午1:24:23 
 */
package com.wxhj.cloud.feignClient.account.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IFaceImageModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeChildrenOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className AccountInfoVO.java
 * @author cya
 * @date 2020年2月12日 下午1:24:23   
*/
@Data
public class AccountInfoVO implements IOrganizeChildrenOrganizeModel,IFaceImageModel {
	@ApiModelProperty(value="账户id")
	private String accountId;
	@ApiModelProperty(value="手机号")
	private String phoneNumber;
	@ApiModelProperty(value="姓名")
	private String name;
	@ApiModelProperty(value="身份证")
	private String idNumber;
	@ApiModelProperty(value="性别")
	private Integer sex;
	@ApiModelProperty(value="账户类型")
	private Integer accountType;
	@ApiModelProperty(value="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@ApiModelProperty(value="累计充值金额")
	private Double rechargeTotalAmount;
	@ApiModelProperty(value=" 累计消费金额")
	private Double consumeTotalAmount;
	@ApiModelProperty(value="累计消费次数")
	private Integer consumeTotalFrequency;
	@ApiModelProperty(value="账户余额")
	private Double accountBalance;
	@ApiModelProperty(value="账户有效期")
	private Date accountValidity;
	@ApiModelProperty(value="备注")
	private String memo;
	@ApiModelProperty(value="是否实名制，0未实名，1已实名")
	private Integer isReal;
	@ApiModelProperty(value="是否人脸注册,0已注册，1未注册")
	private Integer isFace;
	
	@ApiModelProperty(value="组织id(无法排序)")
	private String organizeId;
	@ApiModelProperty(value="组织名称(无法排序)")
	private String organizeName;
	@ApiModelProperty(value="子商户id(无法排序)")
	private String childOrganizeId;
	@ApiModelProperty(value="子商户名字(无法排序)")
	private String childOrganizeName;
	
	
	@ApiModelProperty(value="是否冻结")
	private Integer isFrozen;
	
	@ApiModelProperty(value="其他编号（学号/工号 之类）",example="abcdefg")
	private String otherCode;
	
	@ApiModelProperty(value="人脸图片名称")
	private String imageName;
	@ApiModelProperty(value="人脸图片外网地址")
	private String imageUrl;
	public void setRechargeTotalAmount(Double rechargeTotalAmount) {
		this.rechargeTotalAmount = rechargeTotalAmount / 100.00;
	}

	public void setConsumeTotalAmount(Double consumeTotalAmount) {
		this.consumeTotalAmount = consumeTotalAmount / 100.00;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance / 100.00;
	}
}
