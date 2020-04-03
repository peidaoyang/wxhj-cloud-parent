/** 
 * @fileName: FaceChangeRecService.java  
 * @author: pjf
 * @date: 2019年11月21日 下午1:21:44 
 */

package com.wxhj.cloud.faceServer.service;

import java.util.List;

import com.wxhj.cloud.faceServer.domian.FaceChangeRecDO;

/**
 * @className FaceChangeRecService.java
 * @author pjf
 * @date 2019年11月21日 下午1:21:44
 */

public interface FaceChangeRecService {
	void insertListCascade(List<FaceChangeRecDO> faceChangeRecList);

	Boolean existByMasterId(Long masterId);
	
	List<FaceChangeRecDO> listGreaterThanIndexAndId(String id,Long currentIndex);
}
