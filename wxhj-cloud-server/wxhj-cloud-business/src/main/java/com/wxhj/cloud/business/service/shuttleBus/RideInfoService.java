package com.wxhj.cloud.business.service.shuttleBus;


import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.RideInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.time.LocalDateTime;

/**
 * @ClassName: RideInfoService.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:30:18
 */
public interface RideInfoService {

    PageInfo<RideInfoDO> listPage(IPageRequestModel pageRequestModel, String organizeId, String nameValue, String field,
                                  LocalDateTime startTime, LocalDateTime endTime);

    PageInfo<RideInfoDO> select(IPageRequestModel pageRequestModel, String accountId,
                                LocalDateTime startTime, LocalDateTime endTime);

    void insert(RideInfoDO rideInfo);
}
