package com.wxhj.cloud.feignClient.account.vo;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeChildrenOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountDetailVO implements IOrganizeChildrenOrganizeModel{
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
	private LocalDateTime createTime;
	@ApiModelProperty(value="账户有效期")
	private LocalDateTime accountValidity;
	@ApiModelProperty(value="备注")
	private String memo;
	@ApiModelProperty(value="是否实名制，0未实名，1已实名")
	private Integer isReal;
	@ApiModelProperty(value="是否人脸注册,0已注册，1未注册")
	private Integer isFace;
	@ApiModelProperty(value="其他编码")
	private String otherCode;

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
	
	@ApiModelProperty(value="权限组信息")
	private List<ViewAuthorityAccountVO> authorityList;
	
	@ApiModelProperty(value="人脸图片名称")
	private String imageName;
	@ApiModelProperty(value="人脸图片外网地址")
	private String imageUrl1;
	@ApiModelProperty(value = "卡号")
	private String cardNumber;
}
