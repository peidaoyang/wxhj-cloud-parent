package com.wxhj.cloud.platform.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.view.ViewUserOrganizeDO;
import com.wxhj.cloud.platform.mapper.view.ViewUserOrganizeMapper;
import com.wxhj.cloud.platform.service.ViewUserOrganizeService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ViewUserOrganizeServiceImpl implements ViewUserOrganizeService{
	@Resource
	ViewUserOrganizeMapper viewUserOrganizeMapper;

	@Override
	public PageInfo<ViewUserOrganizeDO> select(IPageRequestModel paginationRequestModel, String keyValue,
			String organizeId) {
		Example example = new Example(ViewUserOrganizeDO.class);
		example.createCriteria().andLike("realName", "%" + keyValue + "%").andEqualTo("isAdmin", 0).andEqualTo("organizeId", organizeId);
		PageInfo<ViewUserOrganizeDO> userInfoList = PageUtil.selectPageList(paginationRequestModel,() -> viewUserOrganizeMapper.selectByExample(example));
		return userInfoList;
	}

//	@Override
//	public IPageResponseModel select(IPageRequestModel paginationRequestModel, String keyValue, String organizeId) {
//		Example example = new Example(ViewUserOrganizeDO.class);
//		example.createCriteria().andLike("realName", "%" + keyValue + "%").andEqualTo("isAdmin", 0)
//				.andEqualTo("organizeId", organizeId);
//
//		PageInfo<ViewUserOrganizeDO> userInfoList = PageUtil.selectPageList(paginationRequestModel,
//				() -> viewUserOrganizeMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(userInfoList, pageDefResponseModel,
//				ViewUserOrganizeDO.class);
//		return pageDefResponseModel;
//	}
	
	
}
