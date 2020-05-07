package com.wxhj.cloud.platform.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.AnnouncementDO;

public interface AnnouncementServcie {
	PageInfo<AnnouncementDO> select(IPageRequestModel pageRequestModel, String organizeId);

	PageInfo<AnnouncementDO> listPage(IPageRequestModel pageRequestModel, String organizeId, String nameValue);

//	AnnouncementDO select(String organizeId);

	AnnouncementDO select(String id);

	String insert(AnnouncementDO announcement, String userid);

	void update(AnnouncementDO announcement);

	void delete(String id);
}
