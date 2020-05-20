package com.wxhj.cloud.platform.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysUserDO;
import com.wxhj.cloud.platform.mapper.SysUserMapper;
import com.wxhj.cloud.platform.service.SysUserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserServiceImpl implements SysUserService {
	@Resource
	SysUserMapper sysUserMapper;

	@Override
	@Transactional
	public String insertCascade(SysUserDO sysUserDO, String userid, List<MapOrganizeUserDO> mapOrganizeUserList) {
		String id = sysUserDO.create(userid);
		if (Strings.isNullOrEmpty(sysUserDO.getUserSecretKey())) {
			String passWord = "";
			String key = UUID.randomUUID().toString().replace("-", "").substring(16);
			passWord = PasswordUtil.calculationPassword(sysUserDO.getUserPassword(), key);
			sysUserDO.setUserSecretKey(key);
			sysUserDO.setUserPassword(passWord);
		}
		sysUserMapper.insert(sysUserDO);
		return id;
	}

	@Override
	@Transactional
	public void updateCascade(SysUserDO sysUserDO, String userid, List<MapOrganizeUserDO> mapOrganizeUserList) {
		sysUserDO.setUserPassword(null);
		sysUserMapper.updateByPrimaryKeySelective(sysUserDO);
	}

	@Override
	public void delete(String id) {
		sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void reSetPassword(SysUserDO sysUserDO) {
		sysUserMapper.updateByPrimaryKeySelective(sysUserDO);
	}

//	@Override
//	public IPageResponseModel selectPageByOrganizeId(IPageRequestModel paginationRequestModel, String keyValue,
//			String organizeId) {
//		Example example = new Example(SysUserDO.class);
//		example.createCriteria().andLike("realName", "%" + keyValue + "%").andEqualTo("isAdmin", 0)
//				.andEqualTo("organizeId", organizeId);
//
//		PageInfo<SysUserDO> userInfoList = PageUtil.selectPageList(paginationRequestModel,
//				() -> sysUserMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(userInfoList, pageDefResponseModel,
//				SysUserDO.class);
//		return pageDefResponseModel;
//	}

	@Override
	public boolean existByAccountId(String accountId) {
		Example example = new Example(SysUserDO.class);
		example.createCriteria().andEqualTo("account", accountId);
		int selectCountByExample = sysUserMapper.selectCountByExample(example);
		return selectCountByExample > 0;
	}

	@Override
	public SysUserDO select(String id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUserDO> select(List<String> userIdList) {
		Example example = new Example(SysUserDO.class);
		example.createCriteria().andIn("id", userIdList);
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public void update(SysUserDO sysUserDO, String userId) {
		sysUserDO.modify(userId);
		sysUserMapper.updateByPrimaryKeySelective(sysUserDO);
	}

	@Override
	public void deleteByOrganizeId(String organizeId) {
		Example example = new Example(SysUserDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		sysUserMapper.deleteByExample(example);
	}
}
