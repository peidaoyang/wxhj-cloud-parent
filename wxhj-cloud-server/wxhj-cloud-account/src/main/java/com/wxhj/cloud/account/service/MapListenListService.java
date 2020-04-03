/** 
 * @fileName: MapListenListService.java  
 * @author: pjf
 * @date: 2019年10月31日 下午1:45:34 
 */

package com.wxhj.cloud.account.service;

/**
 * @className MapListenListService.java
 * @author pjf
 * @date 2019年10月31日 下午1:45:34   
*/

import java.util.List;

import com.wxhj.cloud.account.domain.MapListenListDO;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

public interface MapListenListService {

	void insertListCascade(List<MapListenListDO> mapListenListList);

	IPageResponseModel selectByNoSync(int selectCount);

	int updateByIdSetSync(List<Long> idList);

	// int insertForMapAccountAuthority(int operateType, String sceneId, String
	// authorityId);

	// int insertForMapAuthorityScene(int operateType, String accountId, String
	// authorityId);
}
