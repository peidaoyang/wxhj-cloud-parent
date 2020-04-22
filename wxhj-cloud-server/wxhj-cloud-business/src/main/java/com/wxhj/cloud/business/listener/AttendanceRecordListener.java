/** 
 * @fileName: AttendanceRecordListener.java  
 * @author: pjf
 * @date: 2019年12月17日 下午2:12:48 
 */

package com.wxhj.cloud.business.listener;

import java.util.List;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.business.attendance.AttendanceRecordService;
import com.wxhj.cloud.business.attendance.AttendanceRecordBO;
import com.wxhj.cloud.business.attendance.AttendanceSingleDayBO;
import com.wxhj.cloud.business.bo.AttendanceMatchingBO;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.service.AttendanceDataService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className AttendanceRecordListener.java
 * @author pjf
 * @date 2019年12月17日 下午2:12:48
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.ATTENDANCE_TOPIC)
public class AttendanceRecordListener implements RocketMqListenDoWorkHandle {
	@Resource
	AttendanceDataService attendanceDataService;
	@Resource
	AttendanceRecordService attendanceRecordService;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		// 暂时写在该类下以后提取
		AttendanceRecordBO attendanceRecord = JSON.parseObject(bodyStr, AttendanceRecordBO.class);
		// 查找当前规则
		CurrentAccountAuthorityDO currentAccountAuthority = attendanceRecordService
				.selectCurrentAccountAuthority(attendanceRecord);

		AttendanceMatchingBO attendanceMatching = null;
		// 查找到有匹配构造
		if (currentAccountAuthority != null) {
			List<AttendanceSingleDayBO> attendanceSingleDayList = attendanceRecordService
					.listAttendanceSingleDay(currentAccountAuthority);
			attendanceMatching = attendanceRecordService.mathingAttendanceMatching(attendanceRecord,
					attendanceSingleDayList);
		}
		// 无匹配规则或者未匹配成功	
		if (attendanceMatching == null) {
			attendanceMatching = attendanceRecordService.defAttendanceMatching(attendanceRecord);
		}
		//
		AttendanceDataDO attendanceData = attendanceRecordService.composeAttendanceData(attendanceRecord,
				attendanceMatching);
		attendanceDataService.insertCascade(attendanceData);
	}

}
