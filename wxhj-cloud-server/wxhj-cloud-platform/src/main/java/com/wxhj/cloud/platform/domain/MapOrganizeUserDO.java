/** 
 * @fileName: OrganizeUserDO.java  
 * @author: pjf
 * @date: 2019年10月9日 下午3:37:12 
 */

package com.wxhj.cloud.platform.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className OrganizeUserDO.java
 * @author pjf
 * @date 2019年10月9日 下午3:37:12
 */

@Table(name = "map_organize_user")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapOrganizeUserDO {
	@Id
	private String id;
	private String organizeId;
	private String userId;
	private String roleId;
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (organizeId == null ? 0 : organizeId.hashCode());
        result = 31 * result + (userId == null ? 0 : userId.hashCode());
        result = 31 * result + (roleId == null ? 0 : roleId.hashCode());
        return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		MapOrganizeUserDO mapOrganizeUserTemp = (MapOrganizeUserDO) obj;
		return mapOrganizeUserTemp.getOrganizeId().equals(organizeId) && mapOrganizeUserTemp.getUserId().equals(userId)
				&& mapOrganizeUserTemp.getRoleId().equals(roleId);
	}

	/**
	 * @param organizeId
	 * @param userId
	 * @param roleId
	 */
	public MapOrganizeUserDO(String organizeId, String userId,String roleId) {
		super();
		this.organizeId = organizeId;
		this.userId = userId;
		this.roleId = roleId;
	}
	
	/**
	 * @param organizeId
	 * @param userId
	 */
	public MapOrganizeUserDO(String organizeId, String roleId) {
		super();
		this.organizeId = organizeId;
		this.roleId = roleId;
	}
}
