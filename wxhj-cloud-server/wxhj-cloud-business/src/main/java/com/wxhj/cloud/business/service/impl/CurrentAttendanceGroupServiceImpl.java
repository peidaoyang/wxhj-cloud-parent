/** 
 * @fileName: CurrentAttendanceGroupServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:42:31 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.mapper.CurrentAttendanceGroupMapper;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className CurrentAttendanceGroupServiceImpl.java
 * @author pjf
 * @date 2019年12月19日 下午4:42:31   
*/
/**
 * @className CurrentAttendanceGroupServiceImpl.java
 * @author pjf
 * @date 2019年12月19日 下午4:42:31
 */
@Service
public class CurrentAttendanceGroupServiceImpl implements CurrentAttendanceGroupService {
	@Resource
	CurrentAttendanceGroupMapper currentAttendanceGroupMapper;

	@Override
	public CurrentAttendanceGroupDO selectById(String id) {
		return currentAttendanceGroupMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public String insert(CurrentAttendanceGroupDO currentAttendanceGroupDO) {
		currentAttendanceGroupMapper.insert(currentAttendanceGroupDO);
		return currentAttendanceGroupDO.getId();
	}

//	@Override
//	public void update(CurrentAttendanceGroupDO currentAttendanceGroupDO) {
//		currentAttendanceGroupMapper.updateByPrimaryKeySelective(currentAttendanceGroupDO);
//	}

	@Transactional
	@Override
	public void delete(String id) {
		currentAttendanceGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CurrentAttendanceGroupDO> listById(List<String> id) {
		Example example = new Example(CurrentAttendanceGroupDO.class);
		example.createCriteria().andIn("id", id);
		return currentAttendanceGroupMapper.selectByExample(example);
	}
}
