/**
 * @className EntranceGroupService.java
 * @author jwl
 * @date 2020年1月10日 下午3:35:01
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.EntranceGroupDO;
import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className EntranceGroupService.java
 * @author jwl
 * @date 2020年1月10日 下午3:35:01
 */
public interface EntranceGroupService {
	String insertCascade(EntranceGroupDO entranceGroup, List<EntranceGroupRecDO> entranceGroupRecList);

	void updateCascade(EntranceGroupDO entranceGroup, List<EntranceGroupRecDO> entranceGroupRecList);

	void update(EntranceGroupDO entranceGroup);

	void deleteCascade(String id);

	EntranceGroupDO selectById(String id);

	List<EntranceGroupDO> listByListId(List<String> id);

	PageInfo<EntranceGroupDO> listByNameAndOrganPage(IPageRequestModel pageRequestModel, String fullName,
			String organizeId);

	List<EntranceGroupDO> listByOrganizeId(String organizeId);
}
