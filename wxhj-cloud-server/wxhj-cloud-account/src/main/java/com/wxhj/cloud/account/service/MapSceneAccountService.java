/** 
 * @fileName: MapSceneAccountService.java  
 * @author: pjf
 * @date: 2019年11月27日 下午1:40:58 
 */

package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.MapSceneAccountDO;

import java.util.List;

public interface MapSceneAccountService {

	MapSceneAccountDO selectBySceneIdAndAccountId(String sceneId, String accountId);

	void addTotalCount(String id);

	void subtractTotalCount(String id);

	/**
	 * 根据场景获取账号
	 * @param sceneId
	 * @return
	 */
	List<MapSceneAccountDO> listBySceneId(String sceneId);
}
