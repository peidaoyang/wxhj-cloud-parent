/**
 * @className CurrentAttendanceBO.java
 * @author jwl
 * @date 2019年12月27日 下午5:26:31
 */
package com.wxhj.cloud.business.bo;

import java.util.List;

import lombok.Data;

/**
 * @className CurrentAttendanceBO.java
 * @author jwl
 * @date 2019年12月27日 下午5:26:31
 */
@Data
public class CurrentAttendanceBO {
	private String attendanceId;
	private String authorityId;
	private String organizeId;
	private List<String> sceneIdList;
	private List<String> accountId;
}
