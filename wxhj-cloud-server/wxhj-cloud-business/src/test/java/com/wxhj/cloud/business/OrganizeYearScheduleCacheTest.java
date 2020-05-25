package com.wxhj.cloud.business;

import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.helper.AttendanceGroupHelper;
import com.wxhj.cloud.core.utils.DateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizeYearScheduleCacheTest {
	@Resource
	AttendanceGroupHelper attendanceGroupHelper;

	String organizeYearScheduleId = "22d87e6d-d212-4afd-9ebf-1136e5711f5f";
	String groupId = "3fae88b3-a209-4afe-a811-cbe2d859f572";
	String groupId1 = "测试groupId";

	@Test
	public void test() throws Exception{

		List<OrganizeYearScheduleRecDO> organizeYearScheduleList = new ArrayList<>();
		OrganizeYearScheduleRecDO organizeYearScheduleRec = OrganizeYearScheduleRecDO.builder().day(LocalDate.now()).organizeYearScheduleId(organizeYearScheduleId).status(0)
				.build();
		organizeYearScheduleList.add(organizeYearScheduleRec);

		List<CurrentAttendanceGroupRecDO> list = new ArrayList<>();
		CurrentAttendanceGroupRecDO currentAttendanceGroupRec = new CurrentAttendanceGroupRecDO();
		currentAttendanceGroupRec.setAttendanceDayId("随机id");
		currentAttendanceGroupRec.setAttendanceGroupId(groupId1);
		currentAttendanceGroupRec.setSerialNumber(0);
		list.add(currentAttendanceGroupRec);

		CurrentAttendanceGroupDO currentAttendanceGroup = new CurrentAttendanceGroupDO();
		currentAttendanceGroup.setOrganizeYearScheduleId(organizeYearScheduleId);
		currentAttendanceGroup.setGroupType(2);
		currentAttendanceGroup.setId(groupId1);

		attendanceGroupHelper.setOrganizeYearScheduleRecs(organizeYearScheduleList);
		attendanceGroupHelper.setCurrentAttendanceGroupRecs(list);

//		String attendanceId = attendanceGroupHelper.getCacheAttendanceId(currentAttendanceGroup, DateUtil.getStringDate(date));
		attendanceGroupHelper.deleteCacheAttendanceId(currentAttendanceGroup, DateFormat.getStringDate(LocalDate.now()));
//		System.out.println(attendanceId);
	}
}
