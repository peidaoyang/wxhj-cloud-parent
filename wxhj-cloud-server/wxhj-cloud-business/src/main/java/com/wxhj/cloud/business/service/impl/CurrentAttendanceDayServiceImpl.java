
package com.wxhj.cloud.business.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayDO;
import com.wxhj.cloud.business.mapper.CurrentAttendanceDayMapper;
import com.wxhj.cloud.business.service.CurrentAttendanceDayService;
import tk.mybatis.mapper.entity.Example;
/**
 * @className CurrentAttendanceDayServiceImpl.java
 * @author pjf
 * @date 2019年12月19日 下午4:41:58   
*/

@Service
public class CurrentAttendanceDayServiceImpl implements CurrentAttendanceDayService {
	@Resource
	CurrentAttendanceDayMapper currentAttendanceDayMapper;

	@Override
	public List<CurrentAttendanceDayDO> listByGroupIdAndDayId(String attendanceId, List<String> dayIdList) {
		Example example = new Example(CurrentAttendanceDayDO.class);
		example.createCriteria().andEqualTo("groupId", attendanceId).andIn("dayId", dayIdList);
		return currentAttendanceDayMapper.selectByExample(example);
	}

	@Override
	public List<CurrentAttendanceDayDO> listByGroupId(String attendanceId) {
		Example example = new Example(CurrentAttendanceDayDO.class);
		example.createCriteria().andEqualTo("groupId", attendanceId);
		return currentAttendanceDayMapper.selectByExample(example);
	}
	
	@Override
	public void insert(CurrentAttendanceDayDO currentAttendanceDayDO) {
		currentAttendanceDayMapper.insert(currentAttendanceDayDO);
		//return currentAttendanceDayDO.getId();
	}

	@Override
	public void update(CurrentAttendanceDayDO currentAttendanceDayDO) {
		currentAttendanceDayMapper.updateByPrimaryKeySelective(currentAttendanceDayDO);
	}

	@Override
	public void delete(String id) {
		currentAttendanceDayMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional
	@Override
	public void insertList(List<CurrentAttendanceDayDO> listCurrentAttendanceDayDO) {
		listCurrentAttendanceDayDO.forEach(q -> this.insert(q));
	}

	@Override
	public List<CurrentAttendanceDayDO> listByDayId(List<String> id) {
		Example example = new Example(CurrentAttendanceDayDO.class);
		example.createCriteria().andIn("dayId", id);
		return currentAttendanceDayMapper.selectByExample(example);
	}

}
