/**
 * 
 */
package com.wxhj.cloud.business.service.visitor;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: VisitInfoService.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:29:13
 */
public interface VisitInfoService {
	PageInfo<VisitInfoDO> selectByName(IPageRequestModel pageRequestModel, String organizeId, Date startTime,
			Date endTime);

	void insert(VisitInfoDO visitInfo);
}
