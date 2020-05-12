/**
 * @className EntranceDayServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 上午9:50:28
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.EntranceDayDO;
import com.wxhj.cloud.business.domain.EntranceDayRecDO;
import com.wxhj.cloud.business.mapper.EntranceDayMapper;
import com.wxhj.cloud.business.service.EntranceDayService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className EntranceDayServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 上午9:50:28
 */
@Service
public class EntranceDayServiceImpl implements EntranceDayService {

	@Resource
	EntranceDayMapper entranceDayMapper;
	@Resource
	Mapper dozerBeanMapper;

	@Transactional
	@Override
	public String insertCascade(EntranceDayDO entranceDay, List<EntranceDayRecDO> entranceDayRecList) {
		entranceDayMapper.insert(entranceDay);
		return entranceDay.getId();
	}

	@Transactional
	@Override
	public void updateCascade(EntranceDayDO entranceDay, List<EntranceDayRecDO> entranceDayRecList) {
		//this.delete(entranceDay.getId());

		//this.insert(entranceDay, entranceDayRecList);
		entranceDayMapper.updateByPrimaryKeySelective(entranceDay);
	}

	@Override
	public void delete(String id) {
		entranceDayMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void delete(List<String> id) {
		Example example = new Example(EntranceDayDO.class);
		example.createCriteria().andIn("id", id);
		entranceDayMapper.deleteByExample(example);
	}

	public PageInfo<EntranceDayDO> listByNameAndOrgan(IPageRequestModel pageRequestModel, String organizeId,

			String fullName) {
		Example example = new Example(EntranceDayDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("fullName", "%" + fullName + "%");
		return PageUtil.selectPageList(pageRequestModel, () -> entranceDayMapper.selectByExample(example));

	}

	@Override
	public EntranceDayDO selectById(String id) {

		return entranceDayMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<EntranceDayDO> listByOrganizeId(String organizeId) {
		Example example = new Example(EntranceDayDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return entranceDayMapper.selectByExample(example);
	}

	@Override
	public List<EntranceDayDO> listByIdList(List<String> id) {
		Example example = new Example(EntranceDayDO.class);
		example.createCriteria().andIn("id", id);
		return entranceDayMapper.selectByExample(example);
	}



}
