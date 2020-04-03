/**
 * 
 */
package com.wxhj.cloud.device.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.VersionManageDO;
import com.wxhj.cloud.device.mapper.VersionManageMapper;
import com.wxhj.cloud.device.service.VersionManageService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: VersionManageServiceImpl.java
 * @author: cya
 * @Date: 2020年1月2日 下午4:42:38
 */
@Service
public class VersionManageServiceImpl implements VersionManageService {
	@Resource
	VersionManageMapper versionManageMapper;

	@Override
	public PageInfo<VersionManageDO> versionManageListPage(IPageRequestModel pageRequestModel, Integer deviceType) {
		if (deviceType >= 0) {
			Example example = new Example(VersionManageDO.class);
			example.createCriteria().andEqualTo("deviceType", deviceType);
			PageInfo<VersionManageDO> jobGroupList = PageUtil.selectPageList(pageRequestModel,
					() -> versionManageMapper.selectByExample(example));

			return jobGroupList;
		} else {
			return PageUtil.selectPageList(pageRequestModel, () -> versionManageMapper.selectAll());
		}
	}

	@Override
	public PageInfo<VersionManageDO> versionManageListPage(IPageRequestModel pageRequestModel, String organizeId,
			int deviceType) {
		Example example = new Example(VersionManageDO.class);
		if (deviceType == -1) {
			example.createCriteria().andIn("organizeId", Arrays.asList(organizeId, "0"));
		} else {
			example.createCriteria().andEqualTo("deviceType", deviceType).andIn("organizeId",
					Arrays.asList(organizeId, "0"));
		}
		PageInfo<VersionManageDO> jobGroupList = PageUtil.selectPageList(pageRequestModel,
				() -> versionManageMapper.selectByExample(example));

		return jobGroupList;
	}

	@Override
	public String insert(VersionManageDO versionManage, String userId) {
		versionManage.create(userId);
		versionManageMapper.insert(versionManage);
		return versionManage.getId();
	}

	@Override
	public void updateByReleaseState(String id, String userId) {
		VersionManageDO versionManageDO = new VersionManageDO();
		versionManageDO.setId(id);
		versionManageDO.modify(userId);
		versionManageDO.setReleaseState(1);
		versionManageMapper.updateByPrimaryKeySelective(versionManageDO);
	}

	@Override
	@Transactional
	public void deleteByIdList(List<String> idList) {
		idList.forEach(q -> versionManageMapper.deleteByPrimaryKey(q));
	}

	@Override
	public VersionManageDO selectById(String versionId) {
		Example example = new Example(VersionManageDO.class);
		example.createCriteria().andEqualTo("id", versionId);
		return versionManageMapper.selectOneByExample(example);
	}

	@Override
	public List<VersionManageDO> listByIdList(List<String> versionId) {
		Example example = new Example(VersionManageDO.class);
		example.createCriteria().andIn("id", versionId);
		return versionManageMapper.selectByExample(example);
	}

}
