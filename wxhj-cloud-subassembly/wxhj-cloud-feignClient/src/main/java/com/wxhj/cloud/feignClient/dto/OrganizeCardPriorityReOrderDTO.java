/**
 * 
 */
package com.wxhj.cloud.feignClient.dto;


import com.wxhj.cloud.feignClient.account.vo.OrganizeCardPriorityVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: AccountRequestDTO.java
 * @author: cya
 * @Date: 2019年11月7日 下午2:40:05 
 */
@Data
@ApiModel(description = "登录方式请求对象")
public class OrganizeCardPriorityReOrderDTO extends CommonOrganizeRequestDTO {
	@ApiModelProperty(value = "组织卡交易顺序排序规则")
	private List<OrganizeCardPriorityVO> list;
}
