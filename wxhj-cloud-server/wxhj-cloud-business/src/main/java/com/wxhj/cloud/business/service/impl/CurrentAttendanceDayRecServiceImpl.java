/** 
 * @fileName: CurrnetAttendanceDayRecServiceIml.java  
 * @author: pjf
 * @date: 2019年12月20日 上午9:20:03 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.CurrentAttendanceDayRecDO;
import com.wxhj.cloud.business.mapper.CurrnetAttendanceDayRecMapper;
import com.wxhj.cloud.business.service.CurrentAttendanceDayRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className CurrnetAttendanceDayRecServiceIml.java
 * @author pjf
 * @date 2019年12月20日 上午9:20:03   
*/
/**
 * @className CurrnetAttendanceDayRecServiceIml.java
 * @author pjf
 * @date 2019年12月20日 上午9:20:03
 */
@Service
public class CurrentAttendanceDayRecServiceImpl implements CurrentAttendanceDayRecService {
	@Resource
	CurrnetAttendanceDayRecMapper currnetAttendanceDayRecMapper;

	@Override
	public List<CurrentAttendanceDayRecDO> selectByAttendanceIdAndId(String attendanceId, String id) {
		Example example = new Example(CurrentAttendanceDayRecDO.class);
		example.createCriteria().andEqualTo("attendanceId", attendanceId).andEqualTo("id", id);
		return currnetAttendanceDayRecMapper.selectByExample(example);

	}

	@Override
	public String insert(CurrentAttendanceDayRecDO cuttentAttendanceDayRecDO) {
		currnetAttendanceDayRecMapper.insert(cuttentAttendanceDayRecDO);
		return null;
	}

	@Override
	public void update(CurrentAttendanceDayRecDO currentAttendanceDayRecDO) {
		currnetAttendanceDayRecMapper.updateByPrimaryKeySelective(currentAttendanceDayRecDO);
	}

	@Override
	public void delete(String id) {
		currnetAttendanceDayRecMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void insertList(List<CurrentAttendanceDayRecDO> listCurrent) {
		listCurrent.forEach(q -> this.insert(q));
	}

}
