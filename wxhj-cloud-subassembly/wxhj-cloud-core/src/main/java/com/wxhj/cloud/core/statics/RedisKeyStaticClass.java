/** 
 * @fileName: RedisKeyConfig.java  
 * @author: pjf
 * @date: 2019年10月9日 下午4:33:41 
 */

package com.wxhj.cloud.core.statics;

/**
 * @className RedisKeyConfig.java
 * @author pjf
 * @date 2019年10月9日 下午4:33:41
 */

public class RedisKeyStaticClass {

	public static final String SSO_USER = "redis#sso_user#";
	public static final String SSO_APP_USER = "redis#sso_app_user#";

	public static final String TABLE_REDIS_KEY = "table@";


	public static final String IMG_VERIFICATION="verification:";


//	public static final String ORGANIZE_REDIS_KEY = "redis#organize";
//
//	public static final String MODULE_REDIS_KEY = "redis#module";
//
//	public static final String ROLE_REDIS_KEY = "redis#role";
//
//	public static final String ROLE_AUTHORIZE_REDIS_KEY = "redis#role_authorize";
//
//	public static final String ORGANIZE_AUTHORIZE_REDIS_KEY = "redis#organize_authorize";
//
//	public static final String USER_REDIS_KEY = "redis#user";

	public static final String ATTACK_VERIFICATION_REDIS_KEY = "redis#attack_verification#";

	public static final String MOBILE_PHONE_CODE_REDIS_KEY = "redis#moblie_phone_code";
	
	//public static final String DEVICE_REDIS_KEY = "redis#device#";

	/**
	 * 日志记录，method相关信息
	 */
	public static final String LOG_METHOD_INFO_KEY = "log:method:info";
	/**
	 * uuid相关信息
	 */
	public static final String SIGN_UUID = "sign:uuid:";
	/**
	 * 国家法定节假日信息
	 */
	public static final String NATION_LEGAL_VOCATION = "nationLegalVocation:";

	/**
	 * redis文件夹分层符号
	 */
	public static final String REDIS_FOLDER_SYMBOL = ":";

	
	public static final String FILR_URL_TABLE="redis#file_url_table";
	public static final String  FILR_TIMED_DELETE="redis#timed_delete";
}
