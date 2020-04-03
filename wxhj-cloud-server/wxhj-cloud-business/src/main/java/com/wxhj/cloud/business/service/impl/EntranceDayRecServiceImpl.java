/**
 * @className EntranceDayRecServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 上午9:57:34
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wxhj.cloud.business.domain.EntranceDayRecDO;
import com.wxhj.cloud.business.mapper.EntranceDayRecMapper;
import com.wxhj.cloud.business.service.EntranceDayRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className EntranceDayRecServiceImpl.java
 * @author jwl
 * @date 2020年1月10日 上午9:57:34
 */
@Service
public class EntranceDayRecServiceImpl implements EntranceDayRecService{

	@Resource
	EntranceDayRecMapper entranceDayRecMapper;
	
	@Override
	@Transactional
	public String insert(EntranceDayRecDO entranceDayRec) {
		entranceDayRecMapper.insert(entranceDayRec);
		return null;
	}
	@Transactional
	@Override
	public void insert(List<EntranceDayRecDO> listEntranceDayRec) {
		listEntranceDayRec.forEach(q -> this.insert(q));
	}

	@Override
	public void update(EntranceDayRecDO entranceDayRec) {
		entranceDayRecMapper.updateByPrimaryKeySelective(entranceDayRec);
	}

	@Transactional
	@Override
	public void delete(String id) {
		Example example = new Example(EntranceDayRecDO.class);
		example.createCriteria().andEqualTo("entranceId",id);
		entranceDayRecMapper.deleteByExample(example);
	}

	@Override
	public List<EntranceDayRecDO> listById(String entranceId) {
		Example example = new Example(EntranceDayRecDO.class);
		example.createCriteria().andEqualTo("entranceId",entranceId);
		return entranceDayRecMapper.selectByExample(example);
	}

}
