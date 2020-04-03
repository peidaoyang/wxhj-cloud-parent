/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: ViewRoleOrganizeVO.java
 * @author: cya
 * @Date: 2020年3月20日 下午2:37:59 
 */
@AllArgsConstructor
@Data
public class ViewRoleOrganizeVO {
	@Id
	private String id;
	private String fullName;
	private Integer isDeleteMark;
	private String organizeId;
}
