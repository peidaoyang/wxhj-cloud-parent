/** 
 * @fileName: SceneInfoServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:35:05 
 */

package com.wxhj.cloud.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.view.ViewSceneInfoDO;
import com.wxhj.cloud.platform.mapper.view.ViewSceneInfoMapper;
import com.wxhj.cloud.platform.service.ViewSceneInfoService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className SceneInfoServiceImpl.java
 * @author pjf
 * @date 2019年11月13日 上午10:35:05   
*/
/**
 * @className SceneInfoServiceImpl.java
 * @author pjf
 * @date 2019年11月13日 上午10:35:05
 */
@Service
public class ViewSceneInfoServiceImpl implements ViewSceneInfoService {
	@Resource
	ViewSceneInfoMapper viewSceneInfoMapper;

//	@Override
//	public IPageResponseModel listByOrganizeIdAndScenceNamePage(IPageRequestModel iPageRequestModel, String organizeId,
//			String scenceName) {
//		Example example = new Example(ViewSceneInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("sceneName", "%" + scenceName + "%");
//		PageInfo<ViewSceneInfoDO> selectPageList = PageUtil.selectPageList(iPageRequestModel,
//				() -> viewSceneInfoMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(selectPageList,
//				pageDefResponseModel, ViewSceneInfoDO.class);
//		return pageDefResponseModel;
//	}
	
	@Override
	public PageInfo<ViewSceneInfoDO> listByOrganizeIdAndScenceNamePage(IPageRequestModel iPageRequestModel, String organizeId,
			String scenceName) {
		Example example = new Example(ViewSceneInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("sceneName", "%" + scenceName + "%");
		PageInfo<ViewSceneInfoDO> selectPageList = PageUtil.selectPageList(iPageRequestModel,
				() -> viewSceneInfoMapper.selectByExample(example));
		return selectPageList;
	}
}
