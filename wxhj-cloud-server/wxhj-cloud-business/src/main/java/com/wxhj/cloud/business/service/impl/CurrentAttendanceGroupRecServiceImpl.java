/** 
 * @fileName: CurrentAttendanceGroupRecServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月20日 上午9:19:04 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.mapper.CurrentAttendanceGroupRecMapper;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className CurrentAttendanceGroupRecServiceImpl.java
 * @author pjf
 * @date 2019年12月20日 上午9:19:04   
*/
/**
 * @className CurrentAttendanceGroupRecServiceImpl.java
 * @author pjf
 * @date 2019年12月20日 上午9:19:04
 */
@Service
public class CurrentAttendanceGroupRecServiceImpl implements CurrentAttendanceGroupRecService {
	@Resource
	CurrentAttendanceGroupRecMapper currentAttendanceGroupRecMapper;

	@Override
	public List<CurrentAttendanceGroupRecDO> selectByAttendanceGroupId(String attendanceGroupId) {
		Example example = new Example(CurrentAttendanceGroupRecDO.class);
		example.createCriteria().andEqualTo("attendanceGroupId", attendanceGroupId);
		return currentAttendanceGroupRecMapper.selectByExample(example);
	}

	@Override
	public String insert(CurrentAttendanceGroupRecDO currentAttendanceGroupRecDO) {
		currentAttendanceGroupRecMapper.insert(currentAttendanceGroupRecDO);
		return null;
	}
	
	@Transactional
	@Override
	public void insertList(List<CurrentAttendanceGroupRecDO> listCurrentAttendanceGroupRecDO) {
		listCurrentAttendanceGroupRecDO.forEach(q -> {
			this.insert(q);
		});
	}
	
	@Override
	public void update(CurrentAttendanceGroupRecDO currentAttendanceGroupRecDO) {
		currentAttendanceGroupRecMapper.updateByPrimaryKeySelective(currentAttendanceGroupRecDO);
	}

	@Override
	public void delete(String id) {
		currentAttendanceGroupRecMapper.deleteByPrimaryKey(id);
	}

	

}
