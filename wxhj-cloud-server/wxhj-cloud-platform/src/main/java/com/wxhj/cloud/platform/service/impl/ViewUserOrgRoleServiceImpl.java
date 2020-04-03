/**
 * 
 */
package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.platform.domain.view.ViewUserOrgRoleDO;
import com.wxhj.cloud.platform.mapper.view.ViewUserOrgRoleMapper;
import com.wxhj.cloud.platform.service.ViewUserOrgRoleService;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewUserOrgRoleServiceImpl.java
 * @author: cya
 * @Date: 2020年2月28日 下午5:36:14 
 */
@Service
public class ViewUserOrgRoleServiceImpl implements ViewUserOrgRoleService{
	@Resource
	ViewUserOrgRoleMapper viewUserOrgRoleMapper;

	@Override
	public List<ViewUserOrgRoleDO> select(List<String> orgranizeId, String userId) {
		Example example = new Example(ViewUserOrgRoleDO.class);
		example.createCriteria().andEqualTo("userId",userId).andIn("organizeId", orgranizeId);
		return viewUserOrgRoleMapper.selectByExample(example);
	}

	@Override
	public List<ViewUserOrgRoleDO> list(List<String> userIdList) {
		Example example = new Example(ViewUserOrgRoleDO.class);
		example.createCriteria().andIn("userId", userIdList);
		return viewUserOrgRoleMapper.selectByExample(example);
	}
	
	
}
