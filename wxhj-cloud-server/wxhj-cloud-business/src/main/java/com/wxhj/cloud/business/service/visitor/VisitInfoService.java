/**
 * 
 */
package com.wxhj.cloud.business.service.visitor;



import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.time.LocalDateTime;

/**
 * @ClassName: VisitInfoService.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:29:13
 */
public interface VisitInfoService {
	PageInfo<VisitInfoDO> selectByName(IPageRequestModel pageRequestModel, String organizeId, LocalDateTime startTime,
									   LocalDateTime endTime);

	void insert(VisitInfoDO visitInfo);
}
