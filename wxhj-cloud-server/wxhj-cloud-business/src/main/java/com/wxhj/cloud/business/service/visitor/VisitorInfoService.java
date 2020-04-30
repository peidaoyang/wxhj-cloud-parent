package com.wxhj.cloud.business.service.visitor;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.VisitorInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @ClassName: visitorInfoService.java
 * @author: cya
 * @Date: 2020年2月11日 下午3:26:54
 */
public interface VisitorInfoService {
//	/**
//	 * 审核状态,0:未审核，1：通过，2：拒绝, -1：全部
//	 * @param pageRequestModel
//	 * @param organizeId
//	 * @param nameValue
//	 * @param isCheck
//	 * @param field
//	 * @return
//	 */
//	PageInfo<VisitorInfoDO> listPage(IPageRequestModel pageRequestModel,String organizeId,
//			String nameValue,Integer isCheck,String field);

	PageInfo<VisitorInfoDO> listPage(IPageRequestModel pageRequestModel,List<String> organizeId,
									 String nameValue,Integer isCheck,String field);

	IPageResponseModel listPage(IPageRequestModel pageRequestModel,String nameValue,String field,Integer isCheck,Date startTime,Date endTime);
//	PageInfo<VisitorInfoDO> selectByNameAndIscheck(IPageRequestModel pageRequestModel,String organizeId,
//			String nameValue,Integer isCheck);
//	
//	PageInfo<VisitorInfoDO> selectByAccoutIdAndIscheck(IPageRequestModel pageRequestModel,String organizeId,
//			String nameValue,Integer isCheck);
	
	IPageResponseModel select(IPageRequestModel pageRequestModel,String accountId,Date startTime,Date endTime);
	
	String insert(VisitorInfoDO visitorInfo);

	void update(VisitorInfoDO visitorInfo);

	void check(String id, Integer isCheck,String sceneId);
	
	List<VisitorInfoDO> selectByIdNumberAndSceneId(String isNumber,String sceneId,Date dateTime);
	
	void delete(List<String> idList);
}
