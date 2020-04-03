/** 
 * @fileName: SysRoleAuthorizeServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月16日 下午1:43:29 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.platform.domain.SysRoleAuthorizeDO;
import com.wxhj.cloud.platform.mapper.SysRoleAuthorizeMapper;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className SysRoleAuthorizeServiceImpl.java
 * @author pjf
 * @date 2019年10月16日 下午1:43:29
 */

@Service
public class SysRoleAuthorizeServiceImpl implements SysRoleAuthorizeService {
	@Resource
	SysRoleAuthorizeMapper sysRoleAuthorizeMapper;

	@Override
	public List<SysRoleAuthorizeDO> select() {
		return sysRoleAuthorizeMapper.selectAll();
	}

	@Override
	public List<SysRoleAuthorizeDO> selectByRoleId(String roleId) {
		List<SysRoleAuthorizeDO> sysRoleAuthorizeList = select();
		return sysRoleAuthorizeList.stream().filter(q -> q.getRoleId().equals(roleId)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void insertList(List<SysRoleAuthorizeDO> sysRoleAuthorizeList, String userId) {
		sysRoleAuthorizeList.forEach(q -> {
			q.create(userId);
			sysRoleAuthorizeMapper.insert(q);
		});

	}

//	@Override
//	public void update(SysRoleAuthorizeDO sysRoleAuthorize, String userId) {
//		sysRoleAuthorize.modify(userId);
//		sysRoleAuthorizeMapper.deleteByPrimaryKey(sysRoleAuthorize);
//	}
//	@Transactional
//	@Override
//	public void update(List<SysModuleDO> sysModuleList, String objId, String userId) {
//		deleteByRoleId(objId);
//		for (SysModuleDO sysModuleTemp : sysModuleList) {
//			SysRoleAuthorizeDO sysRoleAuthorize = new SysRoleAuthorizeDO();
//			sysRoleAuthorize.setModuleId(sysModuleTemp.getId());
//			sysRoleAuthorize.create(userId);
//			sysRoleAuthorize.setRoleId(objId);
//			sysRoleAuthorizeMapper.insert(sysRoleAuthorize);
//		}
//	}

	@Override
	public void deleteByRoleId(String roleId) {
		Example example = new Example(SysRoleAuthorizeDO.class);
		example.createCriteria().andEqualTo("roleId", roleId);
		sysRoleAuthorizeMapper.deleteByExample(example);
	}

	@Override
	public void deleteByModuleId(String moduleId) {
		Example example = new Example(SysRoleAuthorizeDO.class);
		example.createCriteria().andEqualTo("moduleId", moduleId);
		sysRoleAuthorizeMapper.deleteByExample(example);

	}
	
	@Override
	public void deleteByOrganizeId(String organizeId) {
		Example example = new Example(SysRoleAuthorizeDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		sysRoleAuthorizeMapper.deleteByExample(example);
	}

	@Override
	public void deleteByIdList(List<String> idList) {
		Example example = new Example(SysRoleAuthorizeDO.class);
		example.createCriteria().andIn("id", idList);
		sysRoleAuthorizeMapper.deleteByExample(example);

	}
	
	

	@Override
	public void deleteByRoleIdListAndModuleIdList(List<String> roleIdList, List<String> moduleIdList) {
		Example example = new Example(SysRoleAuthorizeDO.class);
		example.createCriteria().andIn("roleId", roleIdList).andIn("moduleId", moduleIdList);
		sysRoleAuthorizeMapper.deleteByExample(example);
		
	}
}
