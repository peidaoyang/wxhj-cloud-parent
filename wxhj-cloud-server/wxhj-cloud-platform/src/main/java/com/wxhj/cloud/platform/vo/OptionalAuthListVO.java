/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import java.util.List;

import com.wxhj.cloud.feignClient.account.vo.AuthorityGroupInfoVO;

import lombok.Data;

/**
 * @ClassName: OptionalAuthListVO.java
 * @author: cya
 * @Date: 2020年3月17日 下午5:31:26 
 */
@Data
public class OptionalAuthListVO {
	private String id;
	private String parentId;
	private Integer layers;
	private String encode;
	private String fullName;
	private List<AuthorityGroupInfoVO> authGroupList;
}
