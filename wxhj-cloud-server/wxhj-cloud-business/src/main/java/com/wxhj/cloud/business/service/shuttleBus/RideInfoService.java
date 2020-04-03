package com.wxhj.cloud.business.service.shuttleBus;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.RideInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: RideInfoService.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:30:18
 */
public interface RideInfoService {
//	PageInfo<RideInfoDO> selectByAccountId(IPageRequestModel pageRequestModel, String organizeId, String nameValue,
//			Date startTime, Date endTime);
//
//	PageInfo<RideInfoDO> selectByFlightId(IPageRequestModel pageRequestModel, String organizeId, String nameValue,
//			Date startTime, Date endTime);
	
	PageInfo<RideInfoDO> listPage(IPageRequestModel pageRequestModel, String organizeId, String nameValue,String field,
			Date startTime, Date endTime);

	PageInfo<RideInfoDO> select(IPageRequestModel pageRequestModel, String accountId, Date startTime, Date endTime);

	void insert(RideInfoDO rideInfo);
}
