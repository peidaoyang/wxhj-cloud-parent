/** 
 * @fileName: AuthorityGroupInfoServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月18日 上午11:42:07 
 */

package com.wxhj.cloud.account.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AuthorityGroupInfoDO;
import com.wxhj.cloud.account.mapper.AuthorityGroupInfoMapper;
import com.wxhj.cloud.account.service.AuthorityGroupInfoService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className AuthorityGroupInfoServiceImpl.java
 * @author pjf
 * @date 2019年11月18日 上午11:42:07
 */

@Service
public class AuthorityGroupInfoServiceImpl implements AuthorityGroupInfoService {

	@Resource
	AuthorityGroupInfoMapper authorityGroupInfoMapper;

//	@Override
//	public PageInfo<AuthorityGroupInfoDO> listByOrganizeIdAndFullNamePage(String fullName, String organizeId,
//			IPageRequestModel pageRequestModel) {
//		Example example = new Example(AuthorityGroupInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("fullName", "%" + fullName + "%");
//		PageInfo<AuthorityGroupInfoDO> list = PageUtil.selectPageList(pageRequestModel,
//				() -> authorityGroupInfoMapper.selectByExample(example));
//		return list;
//	}
	
	
	
	
//	@Override
//	public PageInfo<AuthorityGroupInfoDO> listByFullAndOrganizeAndTypePage(String fullName, String organizeId,
//			Integer type,IPageRequestModel pageRequestModel) {
//		Example example = new Example(AuthorityGroupInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("type",type).andLike("fullName", "%" + fullName + "%");
//		PageInfo<AuthorityGroupInfoDO> list = PageUtil.selectPageList(pageRequestModel,
//				() -> authorityGroupInfoMapper.selectByExample(example));
//		return list;
//	}

//	@Override
//	public List<AuthorityGroupInfoDO> listByOrganizeId(String organizeId) {
//		Example example = new Example(AuthorityGroupInfoDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId);
//		return authorityGroupInfoMapper.selectByExample(example);
//	}
	
	@Override
	public List<AuthorityGroupInfoDO> listByOrgAndType(String organizeId,Integer type) {
		Example example = new Example(AuthorityGroupInfoDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andEqualTo("type",type);
		return authorityGroupInfoMapper.selectByExample(example);
	}

	@Override
	public String insertCascade(AuthorityGroupInfoDO authorityGroupInfo,List<String> sceneIdList,
			List<String> accountIdList) {
		authorityGroupInfo.setId(UUID.randomUUID().toString());
		authorityGroupInfoMapper.insert(authorityGroupInfo);
		return authorityGroupInfo.getId();
	}

	@Override
	public void updateCascade(AuthorityGroupInfoDO authorityGroupInfo,List<String> sceneIdList,List<String> accountIdList) {
		authorityGroupInfoMapper.updateByPrimaryKeySelective(authorityGroupInfo);
	}

	@Override
	public void deleteById(String id) {
		authorityGroupInfoMapper.deleteByPrimaryKey(id);

	}

	@Override
	public List<AuthorityGroupInfoDO> listByOrganizeList(List<String> organizeList) {
		Example example = new Example(AuthorityGroupInfoDO.class);
		example.createCriteria().andIn("organizeId", organizeList);
		return authorityGroupInfoMapper.selectByExample(example);
	}


	@Override
	public List<AuthorityGroupInfoDO> list() {
		return authorityGroupInfoMapper.selectAll();
	}

}
