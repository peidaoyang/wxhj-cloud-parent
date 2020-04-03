/** 
 * @fileName: CurrentAuthoritySceneService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:40:28 
 */

package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAuthoritySceneDO;

/**
 * @className CurrentAuthoritySceneService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:28   
*/
/**
 * @className CurrentAuthoritySceneService.java
 * @author pjf
 * @date 2019年12月19日 下午4:40:28
 */

public interface CurrentAuthoritySceneService {

	List<CurrentAuthoritySceneDO> selectBySceneId(String sceneId);

	String insert(CurrentAuthoritySceneDO currentAuthoritySceneDO);

	void insertListCascade(List<CurrentAuthoritySceneDO> currentAuthoritySceneDO,
			List<CurrentAccountAuthorityDO> listAccountAuthority, String attendanceId);

	// , String authorityId
	void update(CurrentAuthoritySceneDO currentAuthoritySceneDO);

	void deleteCascade(String id);
}
