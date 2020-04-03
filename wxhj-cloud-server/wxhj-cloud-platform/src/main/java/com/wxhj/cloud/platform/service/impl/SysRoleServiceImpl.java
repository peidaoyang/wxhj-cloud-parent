/** 
 * @fileName: SysRoleServiceImpl.java  
 * @author: pjf
 * @date: 2019年10月16日 上午11:35:24 
 */

package com.wxhj.cloud.platform.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.SysRoleDO;
import com.wxhj.cloud.platform.mapper.SysRoleAuthorizeMapper;
import com.wxhj.cloud.platform.mapper.SysRoleMapper;
import com.wxhj.cloud.platform.service.SysRoleService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className SysRoleServiceImpl.java
 * @author pjf
 * @date 2019年10月16日 上午11:35:24
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Resource
	SysRoleMapper sysRoleMapper;
	@Resource
	SysRoleAuthorizeMapper sysRoleAuthorizeMapper;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	@Override
	public IPageResponseModel select(String keyWord, IPageRequestModel paginationRequestModel,
			List<String> organizeChildLis) {
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andIn("organizeId", organizeChildLis).andLike("fullName", "%" + keyWord + "%");
		//"sort_code asc",
		PageInfo<SysRoleDO> roleInfoList = PageUtil.selectPageList(paginationRequestModel, 
				 () -> sysRoleMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(roleInfoList,pageDefResponseModel,SysRoleDO.class);
		return pageDefResponseModel;
	}
	
	@Override
	public List<SysRoleDO> listByOrganizeIdList(List<String> organizeIdList) {
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andIn("organizeId", organizeIdList);
		return sysRoleMapper.selectByExample(example);
	}
	
	@Override
	public List<SysRoleDO> selectByOrganizeId(String organizedId){
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andEqualTo("organizeId",organizedId);
		return sysRoleMapper.selectByExample(example);
	}
	
	@Override
	public String insert(SysRoleDO sysRoleDO, String userid) {
		sysRoleDO.create(userid);
		sysRoleDO.setIsAllowDelete(0);
		sysRoleMapper.insert(sysRoleDO);
		return sysRoleDO.getId();
	}
	
	@Override
	public String insertSuperManage(SysRoleDO sysRoleDO, String userid) {
		sysRoleDO.create(userid);
		sysRoleDO.setIsAllowDelete(1);
		sysRoleMapper.insert(sysRoleDO);
		return sysRoleDO.getId();
	}

	@Override
	public void update(SysRoleDO sysRoleDO, String userId) {
		sysRoleMapper.updateByPrimaryKeySelective(sysRoleDO);
	}

	@Override
	public void shamDeleteByKey(String id,String userId) {
		SysRoleDO sysRoleDO = new SysRoleDO();
		sysRoleDO.remove(userId);
		sysRoleDO.setId(id);
		sysRoleMapper.updateByPrimaryKeySelective(sysRoleDO);
	}

	@Override
	public void deleteByOrganizeIdCascade(String organizeId) {
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId);
		sysRoleMapper.deleteByExample(example);
	}

	@Override
	public void delete(String id) {
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andEqualTo("id", id).andEqualTo("isAllowDelete",0);
		sysRoleMapper.deleteByExample(example);
	}

	@Override
	public SysRoleDO selectSuperManage(String organize) {
		Example example = new Example(SysRoleDO.class);
		example.createCriteria().andEqualTo("organizeId",organize).andEqualTo("isAllowDelete", 1);
		return sysRoleMapper.selectOneByExample(example);
	}

}
