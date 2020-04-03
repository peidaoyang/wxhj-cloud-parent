/**
 * @className EntranceDayService.java
 * @author jwl
 * @date 2020年1月10日 上午9:44:33
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.EntranceDayDO;
import com.wxhj.cloud.business.domain.EntranceDayRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className EntranceDayService.java
 * @author jwl
 * @date 2020年1月10日 上午9:44:33
 */
public interface EntranceDayService {
	String insertCascade(EntranceDayDO entranceDay, List<EntranceDayRecDO> entranceDayRec);

	void updateCascade(EntranceDayDO entranceDay, List<EntranceDayRecDO> entranceDayRec);

	void delete(String id);

	void delete(List<String> id);

	EntranceDayDO selectById(String id);

	List<EntranceDayDO> listByIdList(List<String> id);

	PageInfo<EntranceDayDO> listByNameAndOrgan(IPageRequestModel pageRequestModel, String organizeId, String fullName);

	List<EntranceDayDO> listByOrganizeId(String organizeId);
}
