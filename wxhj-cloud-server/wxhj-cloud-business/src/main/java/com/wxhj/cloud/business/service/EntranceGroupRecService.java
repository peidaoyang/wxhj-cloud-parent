/**
 * @className EntranceGroupRecService.java
 * @author jwl
 * @date 2020年1月10日 下午3:35:27
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.wxhj.cloud.business.domain.EntranceGroupRecDO;

/**
 * @className EntranceGroupRecService.java
 * @author jwl
 * @date 2020年1月10日 下午3:35:27
 */
public interface EntranceGroupRecService {
	void insert(EntranceGroupRecDO entranceGroupRecDO);
	
	void insert(List<EntranceGroupRecDO> entranceGroupDO);
	
	void update(EntranceGroupRecDO entranceGroupRecDO);
	
	void updateList(List<EntranceGroupRecDO> entranceGroupRecDO);
	
	void delete(String id);
	
	List<EntranceGroupRecDO> listById(String id);
}
