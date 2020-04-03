/** 
 * @fileName: MapAccountAuthorityDO.java  
 * @author: pjf
 * @date: 2019年10月31日 下午4:36:12 
 */

package com.wxhj.cloud.account.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className MapAccountAuthorityDO.java
 * @author pjf
 * @date 2019年10月31日 下午4:36:12   
*/
/**
 * @className MapAccountAuthorityDO.java
 * @author pjf
 * @date 2019年10月31日 下午4:36:12
 */
@Data
@Table(name = "map_account_authority")
@NoArgsConstructor
@AllArgsConstructor
public class MapAccountAuthorityDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String accountId;

	@Override
	public boolean equals(Object obj) {
		MapAccountAuthorityDO mapAccountAuthorityTemp = (MapAccountAuthorityDO) obj;
		return this.authorityGroupId.equals(mapAccountAuthorityTemp.getAuthorityGroupId())
				&& this.accountId.equals(mapAccountAuthorityTemp.getAccountId());
	}
}
