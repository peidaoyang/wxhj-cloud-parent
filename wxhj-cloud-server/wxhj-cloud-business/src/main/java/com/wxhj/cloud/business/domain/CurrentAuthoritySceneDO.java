/**
 * @className CurrentAuthoritySceneDO.java
 * @admin jwl
 * @date 2019年12月19日 下午3:00:35
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className CurrentAuthoritySceneDO.java
 * @admin jwl
 * @date 2019年12月19日 下午3:00:35
 */
@Data
@ToString
@Table(name = "current_authority_scene")
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAuthoritySceneDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String sceneId;
}
