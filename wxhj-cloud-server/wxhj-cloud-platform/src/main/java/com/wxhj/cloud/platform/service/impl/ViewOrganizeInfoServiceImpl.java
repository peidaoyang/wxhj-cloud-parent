/**
 * 
 */
package com.wxhj.cloud.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.view.ViewOrganizeInfoDO;
import com.wxhj.cloud.platform.mapper.view.ViewOrganizeInfoMapper;
import com.wxhj.cloud.platform.service.ViewOrganizeInfoService;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewOrganizeInfoServiceImpl.java
 * @author: cya
 * @Date: 2020年3月12日 下午5:49:23 
 */
@Service
public class ViewOrganizeInfoServiceImpl implements ViewOrganizeInfoService{
	@Resource
	ViewOrganizeInfoMapper viewOrganizeInfoMapper;

	@Override
	public IPageResponseModel select(IPageRequestModel pageRequestModel, String fullName, String parentId) {
		Example example = new Example(ViewOrganizeInfoDO.class);
		example.createCriteria().andEqualTo("parentId", parentId).andLike("fullName", "%" + fullName + "%")
				.andEqualTo("isDeleteMark", 0);
		PageInfo<ViewOrganizeInfoDO> pageList = PageUtil.selectPageList(pageRequestModel, () -> {
			return viewOrganizeInfoMapper.selectByExample(example);
		});
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageList, pageDefResponseModel,
				ViewOrganizeInfoDO.class);
		return pageDefResponseModel;
	}
	
	
}
