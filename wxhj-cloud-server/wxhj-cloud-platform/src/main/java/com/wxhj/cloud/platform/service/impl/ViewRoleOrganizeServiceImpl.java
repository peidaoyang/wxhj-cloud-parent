/**
 * 
 */
package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.view.ViewRoleOrganizeDO;
import com.wxhj.cloud.platform.mapper.view.ViewRoleOrganizeMapper;
import com.wxhj.cloud.platform.service.ViewRoleOrganizeService;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewRoleOrganizeServiceImpl.java
 * @author: cya
 * @Date: 2019年11月25日 上午11:16:51
 */
@Service
public class ViewRoleOrganizeServiceImpl implements ViewRoleOrganizeService {
	@Resource
	ViewRoleOrganizeMapper viewRoleOrganizeMapper;
	
	
//	@Override
//	public IPageResponseModel listByOrgId(String keyWord, String organizeId, IPageRequestModel paginationRequest) {
//		Example example = new Example(ViewRoleOrganizeDO.class);
//		example.createCriteria().andEqualTo("organizeId",organizeId).andLike("fullName", "%" + keyWord + "%");
//		PageInfo<ViewRoleOrganizeDO> roleInfoList = PageUtil.selectPageList(paginationRequest,
//				() -> viewRoleOrganizeMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(roleInfoList, pageDefResponseModel,
//				ViewRoleOrganizeDO.class);
//		return pageDefResponseModel;
//	}
	
	@Override
	public IPageResponseModel listByOrgId(String keyWord, List<String> organizeIdList, IPageRequestModel paginationRequest) {
		Example example = new Example(ViewRoleOrganizeDO.class);
		example.createCriteria().andIn("organizeId",organizeIdList).andLike("fullName", "%" + keyWord + "%");
		
		PageInfo<ViewRoleOrganizeDO> roleInfoList = PageUtil.selectPageList(paginationRequest,() -> viewRoleOrganizeMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(roleInfoList, pageDefResponseModel,ViewRoleOrganizeDO.class);
		
		return pageDefResponseModel;
	}
	
	@Override
	public List<ViewRoleOrganizeDO> listByOrgList(List<String> orgList) {
		Example example = new Example(ViewRoleOrganizeDO.class);
		example.createCriteria().andIn("organizeId", orgList);
		return viewRoleOrganizeMapper.selectByExample(example);
	}
	
	@Override
	public List<ViewRoleOrganizeDO> listByOrgId(String orgId) {
		Example example = new Example(ViewRoleOrganizeDO.class);
		example.createCriteria().andEqualTo("organizeId", orgId);
		return viewRoleOrganizeMapper.selectByExample(example);
	}
	
	@Override
	public boolean exitsRoleByOrg(String orgId) {
		Example example = new Example(ViewRoleOrganizeDO.class);
		example.createCriteria().andEqualTo("organizeId", orgId);
		return viewRoleOrganizeMapper.selectByExample(example).size()>0?false:true;
	}
}
