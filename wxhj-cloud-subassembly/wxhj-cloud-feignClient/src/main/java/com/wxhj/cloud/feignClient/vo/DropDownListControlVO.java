/** 
 * @fileName: SysOrganizeSimpleVO.java  
 * @author: pjf
 * @date: 2019年11月13日 上午11:39:46 
 */

package com.wxhj.cloud.feignClient.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className SysOrganizeSimpleVO.java
 * @author pjf
 * @date 2019年11月13日 上午11:39:46
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DropDownListControlVO {
	@ApiModelProperty(value="权限组id")
	private String id;
	@ApiModelProperty(value="权限组名称")
	private String fullName;
	@ApiModelProperty(value="组织编号")
	private String organizeId;

	@Override
	public boolean equals(Object obj) {
		DropDownListControlVO DropDownListControlVO = (DropDownListControlVO) obj;
		return this.id.equals(DropDownListControlVO.getId());
	}
}
