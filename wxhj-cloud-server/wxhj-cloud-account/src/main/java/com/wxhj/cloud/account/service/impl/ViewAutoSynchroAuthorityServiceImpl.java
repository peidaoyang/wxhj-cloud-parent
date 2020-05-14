package com.wxhj.cloud.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AuthorityGroupInfoDO;
import com.wxhj.cloud.account.domain.view.ViewAutoSynchroAuthorityDO;
import com.wxhj.cloud.account.mapper.view.ViewAutoSynchroAuthorityMapper;
import com.wxhj.cloud.account.service.ViewAutoSynchroAuthorityService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class ViewAutoSynchroAuthorityServiceImpl implements ViewAutoSynchroAuthorityService{
	@Resource
	ViewAutoSynchroAuthorityMapper viewAutoSynchroAuthorityMapper;

	@Override
	public List<ViewAutoSynchroAuthorityDO> list(String organizeId,Integer type,Integer autoSychro) {
		Example example = new Example(ViewAutoSynchroAuthorityDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andEqualTo("autoSynchro",autoSychro).andEqualTo("type",type);
		return viewAutoSynchroAuthorityMapper.selectByExample(example);
	}

	@Override
	public List<ViewAutoSynchroAuthorityDO> listByOrgIdAndAutoSychro(String organizeId, Integer autoSychro) {
		Example example = new Example(ViewAutoSynchroAuthorityDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andEqualTo("autoSynchro",autoSychro);
		return viewAutoSynchroAuthorityMapper.selectByExample(example);
	}

	@Override
	public List<ViewAutoSynchroAuthorityDO> listByIdList(List<String> idList) {
		Example example = new Example(ViewAutoSynchroAuthorityDO.class);
		example.createCriteria().andIn("id",idList);
		return viewAutoSynchroAuthorityMapper.selectByExample(example);
	}

	@Override
	public int listNotInId(String id, String organizeId, Integer type, Integer autoSychro) {
		Example example = new Example(ViewAutoSynchroAuthorityDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId).andEqualTo("autoSynchro",autoSychro).andNotEqualTo("id",id);
		return viewAutoSynchroAuthorityMapper.selectCountByExample(example);
	}

	@Override
	public PageInfo<ViewAutoSynchroAuthorityDO> listByFullAndOrganizeAndTypePage(String fullName, String organizeId,
			Integer type,IPageRequestModel pageRequestModel) {
		Example example = new Example(AuthorityGroupInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("type",type).andLike("fullName", "%" + fullName + "%");
		PageInfo<ViewAutoSynchroAuthorityDO> list = PageUtil.selectPageList(pageRequestModel,() -> viewAutoSynchroAuthorityMapper.selectByExample(example));
		return list;
	}
}
