/** 
 * @fileName: AuthenticationTokenBO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午2:28:51 
 */

package com.wxhj.cloud.platform.bo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className AuthenticationTokenBO.java
 * @author pjf
 * @date 2019年12月10日 下午2:28:51
 */

@Data
public class AuthenticationTokenBO {
	@ApiModelProperty(value="用户名")
	private String userName;

	@ApiModelProperty(value="用户id")
	private String userId;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="用户关联组织的权限id")
	private String mapId;
	@ApiModelProperty(value="当前组织id")
	private String currentOrganizeId;
	//@ApiModelProperty(value="子组织id")
	//private List<String> organizeChildList;
	@ApiModelProperty(value="是否是系统管理员")
	private Boolean isSystem;
	@ApiModelProperty(value="sessionId")
	private String sessionId;
	@ApiModelProperty(value = "组织类型")
	private Integer orgType;
}
