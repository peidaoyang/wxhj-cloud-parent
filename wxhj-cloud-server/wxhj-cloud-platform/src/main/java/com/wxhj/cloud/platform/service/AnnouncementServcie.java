package com.wxhj.cloud.platform.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.platform.domain.AnnouncementDO;

public interface AnnouncementServcie {
	PageInfo<AnnouncementDO> select(IPageRequestModel pageRequestModel, String organizeId);

	AnnouncementDO select(String organizeId);

	String insert(AnnouncementDO announcement, String userid);

	void update(AnnouncementDO announcement);

	void delete(String id);
}
