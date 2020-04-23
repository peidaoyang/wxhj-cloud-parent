/** 
 * @fileName: SubmitAccountInfoRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月19日 上午11:42:12 
 */

package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SubmitAccountInfoRequestDTO.java
 * @author pjf
 * @date 2019年11月19日 上午11:42:12   
*/
/**
 * @className SubmitAccountInfoRequestDTO.java
 * @author pjf
 * @date 2019年11月19日 上午11:42:12
 */
@Data
@ToString
@ApiModel(description = "添加/修改用户权限对象")
public class SubmitAccountInfoRequestDTO {
	@ApiModelProperty(value = "用户id", example = "0000000008")
	@NotBlank(message = "不能为空")
	private String accountId;
	@ApiModelProperty(value="姓名",example="测试")
	@NotBlank
	private String name;
	@ApiModelProperty(value="性别",example="0为男,1为女")
	@Min(0)
	@Max(10)
	private Integer sex;
	@ApiModelProperty(value="身份证",example="321183199212021111")
	@NotBlank
	private String idNumber;
	@ApiModelProperty(value="手机号",example="19952212111")
	@NotBlank
	private String phoneNumber;
	
	
	@ApiModelProperty(value = "子商户")
	@NotBlank(message = "不能为空")
	private String childOrganizeId;
	@ApiModelProperty(value = "用户权限组idlist")
//	@NotEmpty(message = "不能为空")
	private List<String> authorityGroupIdList;
	
	@ApiModelProperty(value="其他编号（学号/工号 之类）",example="abcdefg")
	private String otherCode;

	@ApiModelProperty(value = "卡号",example = "sb0000317")
	private String cardNumber;
}
