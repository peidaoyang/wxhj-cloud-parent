package com.wxhj.cloud.business.dto.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import lombok.Data;

@Data
public class ListMapAttendanceAuthorizeResponseDTO implements IOrganizeModel{
	private String attendanceId;
	private String authorityId;
	private String fullName;
	private String organizeId;
	private String organizeName;
	private String attendanceName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date applyDate;
}
