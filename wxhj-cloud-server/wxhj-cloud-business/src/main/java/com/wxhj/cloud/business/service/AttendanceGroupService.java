/**
 * @className AttendanceGroupService.java
 * @admin jwl
 * @date 2019年12月13日 上午11:38:41
 */
package com.wxhj.cloud.business.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceGroupDO;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className AttendanceGroupService.java
 * @admin jwl
 * @date 2019年12月13日 上午11:38:41
 */
public interface AttendanceGroupService {
	String insertCascade(AttendanceGroupDO attendanceGroup, List<AttendanceGroupRecDO> attendanceGroupRecList);

	void updateCascade(AttendanceGroupDO attendanceGroup, List<AttendanceGroupRecDO> attendanceGroupRecList);

	void update(AttendanceGroupDO attendanceGroup);

	void deleteCascade(String id);

	PageInfo<AttendanceGroupDO> listAttendanceGroup(IPageRequestModel paeIPageRequestModel, String fullName,
			String organizeId, Integer studentGroup);

	AttendanceGroupDO selectAttendanceGroupById(String id);

	List<AttendanceGroupDO> listAll(String organizeId);

	List<AttendanceGroupDO> listByAttendanceGroupName(String fullName);

	List<AttendanceGroupDO> listInAtendanceGroupId(List<String> atendanceGroupId);
}
