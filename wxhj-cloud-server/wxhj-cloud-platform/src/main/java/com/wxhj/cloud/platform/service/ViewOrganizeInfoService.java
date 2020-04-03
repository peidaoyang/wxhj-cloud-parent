/**
 * 
 */
package com.wxhj.cloud.platform.service;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;

/**
 * @ClassName: ViewOrganizeInfoService.java
 * @author: cya
 * @Date: 2020年3月12日 下午5:48:59 
 */
public interface ViewOrganizeInfoService {
	IPageResponseModel select(IPageRequestModel pageRequestModel, String fullName,
			String parentid);
}
