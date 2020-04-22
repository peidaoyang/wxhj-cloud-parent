package com.wxhj.cloud.business.attendance;

import java.util.List;

import com.wxhj.cloud.business.bo.AttendanceMatchingBO;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;

public interface AttendanceRecordService {
	// 查找匹配的规则
	CurrentAccountAuthorityDO selectCurrentAccountAuthority(AttendanceRecordBO attendanceRecord);

	// 根据当前规则查询当日规则
	List<AttendanceSingleDayBO> listAttendanceSingleDay(CurrentAccountAuthorityDO currentAccountAuthority);

	AttendanceMatchingBO mathingAttendanceMatching(AttendanceRecordBO attendanceRecord,
			List<AttendanceSingleDayBO> attendanceSingleDayList);

	AttendanceMatchingBO defAttendanceMatching(AttendanceRecordBO attendanceRecord);

	// 组合并插入
	AttendanceDataDO composeAttendanceData(AttendanceRecordBO attendanceRecord,
			AttendanceMatchingBO attendanceMatching);
}
