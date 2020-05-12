/**
 * @className EntranceGroupServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 下午3:41:25
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.EntranceGroupDO;
import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.business.mapper.EntranceGroupMapper;
import com.wxhj.cloud.business.service.EntranceGroupService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className EntranceGroupServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 下午3:41:25
 */
@Service
public class EntranceGroupServiceImpl implements EntranceGroupService {

	@Resource
	EntranceGroupMapper entranceGroupMapper;
	@Resource
	Mapper dozerBeanMapper;

	@Transactional
	@Override
	public String insertCascade(EntranceGroupDO entranceGroup, List<EntranceGroupRecDO> entranceGroupRecList) {
		entranceGroupMapper.insert(entranceGroup);
		return entranceGroup.getId();
	}

	@Override
	@Transactional
	public void updateCascade(EntranceGroupDO entranceGroup, List<EntranceGroupRecDO> entranceGroupRecList) {
		entranceGroupMapper.updateByPrimaryKeySelective(entranceGroup);
	}

	@Override
	public void deleteCascade(String id) {
		entranceGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<EntranceGroupDO> listByNameAndOrganPage(IPageRequestModel pageRequestModel, String fullName,
			String organizeId) {

		Example example = new Example(EntranceGroupDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("fullName", "%" + fullName + "%");
		return PageUtil.selectPageList(pageRequestModel, () -> entranceGroupMapper.selectByExample(example));

	}

	@Override
	public EntranceGroupDO selectById(String id) {

		return entranceGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<EntranceGroupDO> listByOrganizeId(String organizeId) {
		Example example = new Example(EntranceGroupDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return entranceGroupMapper.selectByExample(example);
	}

	@Override
	public List<EntranceGroupDO> listByListId(List<String> id) {
		Example example = new Example(EntranceGroupDO.class);
		example.createCriteria().andIn("id", id);
		return entranceGroupMapper.selectByExample(example);
	}

	@Override
	public void update(EntranceGroupDO entranceGroup) {
		entranceGroupMapper.updateByPrimaryKeySelective(entranceGroup);

	}

}
