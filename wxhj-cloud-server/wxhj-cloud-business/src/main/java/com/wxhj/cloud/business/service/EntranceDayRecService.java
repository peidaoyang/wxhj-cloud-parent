/**
 * @className EntranceDayRecService.java
 * @author jwl
 * @date 2020年1月10日 上午9:46:34
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.EntranceDayRecDO;

/**
 * @className EntranceDayRecService.java
 * @author jwl
 * @date 2020年1月10日 上午9:46:34
 */
public interface EntranceDayRecService {
	String insert(EntranceDayRecDO entranceDayRec);
	
	void insert(List<EntranceDayRecDO> listEntranceDayRec);
	
	void update(EntranceDayRecDO entranceDayRec);
	
	void delete(String id);
	
	List<EntranceDayRecDO> listById(String entranceId);
}
