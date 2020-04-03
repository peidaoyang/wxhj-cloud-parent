/** 
 * @fileName: NowAttendanceAccountMapper.java  
 * @author: pjf
 * @date: 2019年12月27日 上午10:23:43 
 */

package com.wxhj.cloud.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.wxhj.cloud.business.domain.NowAttendanceAccountDO;
import com.wxhj.cloud.driud.common.BaseMapper;

/**
 * @className NowAttendanceAccountMapper.java
 * @author pjf
 * @date 2019年12月27日 上午10:23:43
 */

@Mapper
public interface NowAttendanceAccountMapper extends BaseMapper<NowAttendanceAccountDO> {

//	@Delete("delete from now_attendance_account where datetime=curdate()")
//	void clearCurdate();
//
//	@Insert({"insert into now_attendance_account",
//		"SELECT id,authority_group_id,account_id,serial_number,attendance_day_id,attendance_type,datetime,name,organize_id,attendance_group_name,attendance_day_name,sequence from view_now_attendance_account"})
//	void insertDef();
	@Select("call fun_attendance_summary(#{datetime})")
	@Options(statementType=StatementType.CALLABLE)
	void inital(@Param("datetime") String datetime);
}
