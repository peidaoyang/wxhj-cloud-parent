/** 
 * @fileName: OtherStaticConfig.java  
 * @author: pjf
 * @date: 2019年10月11日 下午4:30:59 
 */

package com.wxhj.cloud.core.statics;

public class OtherStaticClass {
	public static final String SSO_WEB_HEAD = "SSO-Token";
	public static final String LOG_SESSION_ID = "logSessionId";
	public static final String ATTACK_VERIFICATION_HEAD = "attack-verification";

	public static final String AUTO_SYNCHRO = "autoSynchro";

	// 1440 minite, 24 hour
	public static int SSO_REDIS_EXPIRE_MINITE = 1440;
	/**
	 * 一天的时间的分钟数
	 */
	public static final Integer ONE_DAY_MINUTE = 1440;
	/**
	 * 两天的时间的分钟数
	 */
	public static final Integer TWO_DAY__MINUTE = 2880;

}
