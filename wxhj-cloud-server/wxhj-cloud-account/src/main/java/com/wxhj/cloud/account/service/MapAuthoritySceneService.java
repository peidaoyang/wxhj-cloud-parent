/** 
 * @fileName: MapAuthoritySceneService.java  
 * @author: pjf
 * @date: 2019年10月31日 下午4:51:52 
 */

package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;

/**
 * @className MapAuthoritySceneService.java
 * @author pjf
 * @date 2019年10月31日 下午4:51:52
 */

public interface MapAuthoritySceneService {

	String insertCascade(MapAuthoritySceneDO mapAuthoritySceneDO);

	int deleteCascade(String authorityGroupId, String sceneId);

	List<MapAuthoritySceneDO> list(MapAuthoritySceneDO mapAuthoritySceneDO);

	List<MapAuthoritySceneDO> listByAuthorityId(String authorityId);

	// 当submitType为0时主id为authGroupId,辅助id为sceneId;为0时相反
	void update(Integer submitType, List<String> mainId, String assistId);
}
