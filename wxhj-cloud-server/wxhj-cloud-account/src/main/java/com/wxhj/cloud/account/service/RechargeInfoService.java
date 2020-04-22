/**
 * 
 */
package com.wxhj.cloud.account.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: RechargeInfoService.java
 * @author: cya
 * @Date: 2020年1月31日 下午3:21:50 
 */
public interface RechargeInfoService {
	String insert(RechargeInfoDO rechargeInfo,String userId);
	
	PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel pageRequestModel,String nameValue,
			Integer type,Integer payType,Date startTime,Date endTime,String organizeId);
	
	PageInfo<RechargeInfoDO> listRechargeInfo(IPageRequestModel iPageRequestModel,Date startTime,
			Date endTime,String accountId);

	void delete(String id);

	List<RechargeInfoDO> list(String organizeId,Date time);

	void revoke(String id,Integer isRevoke);
}
