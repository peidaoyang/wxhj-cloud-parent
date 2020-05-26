/**
 * @fileName: ViewAccountConsumeService.java
 * @author: pjf
 * @date: 2020年2月5日 上午11:06:19
 */

package com.wxhj.cloud.account.service;


import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewAccountConsumeDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className ViewAccountConsumeService.java
 * @author pjf
 * @date 2020年2月5日 上午11:06:19
 */

public interface ViewAccountConsumeService {
    PageInfo<ViewAccountConsumeDO> listPage(IPageRequestModel iPageRequestModel, String organizeId, String name,
                                            LocalDateTime beginTime, LocalDateTime endTime, Integer cardType);


    PageInfo<ViewAccountConsumeDO> listByTimeAndAccountPage(IPageRequestModel iPageRequestModel, String accountId,
                                                            LocalDateTime beginTime, LocalDateTime endTime, Integer cardType);


    List<ViewAccountConsumeDO> list(String organizeId, String name, LocalDateTime beginTime, LocalDateTime endTime);

}
