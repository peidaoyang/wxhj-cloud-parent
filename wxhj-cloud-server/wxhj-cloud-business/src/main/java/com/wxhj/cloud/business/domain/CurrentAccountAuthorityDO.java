/**
 * @className CurrentAccountAuthorityDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:54:12
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className CurrentAccountAuthorityDO.java
 * @admin jwl
 * @date 2019年12月19日 下午2:54:12
 */
@Data
@ToString
@Table(name = "current_account_authority")
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccountAuthorityDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String accountId;
	private String name;
}
