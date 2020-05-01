package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;
import com.wxhj.cloud.platform.mapper.SysOrganizeAuthorizeMapper;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysOrganizeAuthorizeServiceImpl implements SysOrganizeAuthorizeService {
	@Resource
	SysOrganizeAuthorizeMapper organizeAuthorizeMapper;

	@Override
	@Transactional
	public void insertList(List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList, String userId) {
		sysOrganizeAuthorizeList.forEach(q -> {
			q.create(userId);
			organizeAuthorizeMapper.insert(q);
		});
	}

	@Override
	public List<SysOrganizeAuthorizeDO> selectByOrganizeId(String id) {
		Example example = new Example(SysOrganizeAuthorizeDO.class);
		example.createCriteria().andEqualTo("organizeId", id);
		return organizeAuthorizeMapper.selectByExample(example);
	}

	@Override
	public void deleteByOrgListAndModuleList(List<String> organizeIdList, List<String> moduleList) {
		Example example = new Example(SysOrganizeAuthorizeDO.class);
		example.createCriteria().andIn("organizeId", organizeIdList).andIn("moduleId", moduleList);
		organizeAuthorizeMapper.deleteByExample(example);
	}

	@Override
	public void deleteByOrganizeId(String organizeId) {
		Example example = new Example(SysOrganizeAuthorizeDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		organizeAuthorizeMapper.deleteByExample(example);
	}

	@Override
	public void deleteByModuleId(String moduleId) {
		Example example = new Example(SysOrganizeAuthorizeDO.class);
		example.createCriteria().andEqualTo("moduleId", moduleId);
		organizeAuthorizeMapper.deleteByExample(example);
	}

	@Override
	public void inserDef(String organizeId, String userId) {
		organizeAuthorizeMapper.insertDefalut(organizeId, userId);
	}

	@Override
	public void insert(String moduleId,String organizeId, String userId) {
		SysOrganizeAuthorizeDO sysOrganizeAuthorizeDO = new SysOrganizeAuthorizeDO(moduleId,organizeId);
		sysOrganizeAuthorizeDO.create(userId);
		organizeAuthorizeMapper.insert(sysOrganizeAuthorizeDO);
	}

}
