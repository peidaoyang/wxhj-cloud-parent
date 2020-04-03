package com.wxhj.cloud.business.bo;

import java.util.List;

import com.wxhj.cloud.feignClient.business.bo.AttendanceGroupRecBO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceGroupBO {
	private String id;
	private String fullName;
	private Integer groupType;


	private String organizeId;
	private List<AttendanceGroupRecBO> attendanceGroupRecList;
}
