package com.wxhj.cloud.business.attendance;

public class AttendanceStaticClass {
	public final static int DAY_MINUTE = 1440;
	public final static Long DAY_SECOND = 86400L;
	public final static Long WEEK_DAY_SECOND = 7 * DAY_SECOND;
	
	
	// 上班前状态
	public final static int BEF_UP_WORK = 1;
	// 上班时间段中
	public final static int MID_UP_WORK = 2;
	// 上班中
	public final static int MID_WORK = 3;
	// 下班时间段中
	public final static int MID_DOWN_WORK = 4;
	// 下班时间段后
	public final static int AFTER_DOWN_WORK = 5;
	// 周末时间段
	public final static int MID_WEEK = 6;
}
