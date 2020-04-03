/** 
 * @fileName: SceneInfoService.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:34:05 
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.SceneInfoDO;

/**
 * @className SceneInfoService.java
 * @author pjf
 * @date 2019年11月13日 上午10:34:05
 */

public interface SceneInfoService {
	List<SceneInfoDO> listByOrganizeId(String organizeId);
	
	List<SceneInfoDO> select();

	List<SceneInfoDO> listByIdList(List<String> sceneIdList);

	SceneInfoDO selectById(String id);

	void delete(String id);

	void update(SceneInfoDO sceneInfo);

	String insert(SceneInfoDO sceneInfo);
}
