/**
 *
 */
package com.wxhj.cloud.account.service;


import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: RechargeInfoService.java
 * @author: cya
 * @Date: 2020年1月31日 下午3:21:50 
 */
public interface RechargeInfoService {
    String insert(RechargeInfoDO rechargeInfo, String userId);

    PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel pageRequestModel, String nameValue,
                                              Integer type, Integer payType, LocalDateTime startTime, LocalDateTime endTime, String organizeId, Integer cardType);

    PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel iPageRequestModel, LocalDateTime startTime,
                                              LocalDateTime endTime, String accountId, Integer cardType);

    void delete(String id);

    List<RechargeInfoDO> list(String organizeId, LocalDateTime time);

    void revoke(String id, Integer isRevoke);
}
