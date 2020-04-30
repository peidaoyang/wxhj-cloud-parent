/**
 * @className EntranceGroupRecServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 下午3:44:51
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.EntranceGroupRecDO;
import com.wxhj.cloud.business.mapper.EntranceGroupRecMapper;
import com.wxhj.cloud.business.service.EntranceGroupRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className EntranceGroupRecServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 下午3:44:51
 */
@Service
public class EntranceGroupRecServiceImpl implements EntranceGroupRecService {

	@Resource
	EntranceGroupRecMapper entranceGroupRecMapper;
	
	@Override
	public void insert(EntranceGroupRecDO entranceGroupRecDO) {
		entranceGroupRecMapper.insert(entranceGroupRecDO);
	}

	@Override
	@Transactional
	public void insert(List<EntranceGroupRecDO> entranceGroupDO) {
		entranceGroupDO.forEach(q -> this.insert(q));
	}
	
	@Override
	public void update(EntranceGroupRecDO entranceGroupRecDO) {
		entranceGroupRecMapper.updateByPrimaryKeySelective(entranceGroupRecDO);
	}

//	@Override
//	@Transactional
//	public void updateList(List<EntranceGroupRecDO> entranceGroupRecDO) {
//		entranceGroupRecDO.forEach(q -> this.update(q));
//	}
	
	@Override
	public void delete(String id) {
		Example example = new Example(EntranceGroupRecDO.class);
		example.createCriteria().andEqualTo("entranceGroupId",id);
		entranceGroupRecMapper.deleteByExample(example);
	}

	@Override
	public List<EntranceGroupRecDO> listById(String id) {
		Example example = new Example(EntranceGroupRecDO.class);
		example.createCriteria().andEqualTo("entranceGroupId",id);
		return entranceGroupRecMapper.selectByExample(example);
	}

	@Override
	public int countByEntranceGroupId(String entranceGroupId) {
		Example example = new Example(EntranceGroupRecDO.class);
		example.createCriteria().andEqualTo("entranceDayId",entranceGroupId);
		return entranceGroupRecMapper.selectCountByExample(example);
	}

}
