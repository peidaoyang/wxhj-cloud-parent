/**
 * 
 */
package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.EnumManageDO;

/**
 * @ClassName: EnumManageService.java
 * @author: cya
 * @Date: 2020年1月8日 上午11:21:55
 */
public interface EnumManageService {
	IPageResponseModel selectEnumList(IPageRequestModel pageRequestModel, String enumName);

	String insert(EnumManageDO enumManage);

	// void insertList(List<EnumManageDO> enumList);
	void delete(String id);

	void update(EnumManageDO enumManage);

	List<EnumManageDO> selectByEnumCode(Integer enumCode);
	
	List<EnumManageDO> listByEnumcodeAndEnumType(Integer enumCode,List<Integer> enumType);
}
