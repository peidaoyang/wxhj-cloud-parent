/** 
 * @fileName: MapAuthoritySceneDO.java  
 * @author: pjf
 * @date: 2019年10月31日 下午4:39:28 
 */

package com.wxhj.cloud.account.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className MapAuthoritySceneDO.java
 * @author pjf
 * @date 2019年10月31日 下午4:39:28
 */
@Table(name = "map_authority_scene")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapAuthoritySceneDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String sceneId;
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapAuthoritySceneDO other = (MapAuthoritySceneDO) obj;
		if (authorityGroupId == null) {
			if (other.authorityGroupId != null)
				return false;
		} else if (!authorityGroupId.equals(other.authorityGroupId))
			return false;
		if (sceneId == null) {
			if (other.sceneId != null)
				return false;
		} else if (!sceneId.equals(other.sceneId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorityGroupId == null) ? 0 : authorityGroupId.hashCode());
		result = prime * result + ((sceneId == null) ? 0 : sceneId.hashCode());
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		MapAuthoritySceneDO mapAuthoritySceneTemp = (MapAuthoritySceneDO) obj;
//
//		return authorityGroupId.equals(mapAuthoritySceneTemp.getAuthorityGroupId())
//				&& sceneId.equals(mapAuthoritySceneTemp.getSceneId());
//	}
	

	
}
