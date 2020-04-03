/** 
 * @fileName: SceneInfoService.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:34:05 
 */

package com.wxhj.cloud.platform.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.platform.domain.view.ViewSceneInfoDO;

/**
 * @className SceneInfoService.java
 * @author pjf
 * @date 2019年11月13日 上午10:34:05
 */

public interface ViewSceneInfoService {

//	IPageResponseModel listByOrganizeIdAndScenceNamePage(IPageRequestModel pageRequestModel, String organizeId,
//			String scenceName);

	PageInfo<ViewSceneInfoDO> listByOrganizeIdAndScenceNamePage(IPageRequestModel pageRequestModel, String organizeId,
			String scenceName);
}
