/** 
 * @fileName: WebResponseState.java  
 * @author: pjf
 * @date: 2019年10月11日 上午9:53:27 
 */

package com.wxhj.cloud.core.enums;

/**
 * 错误类型对应业务类型
 * 		401xx -	系统级别错误
 * 		402xx - 参数级别错误
 * 		403xx - business业务错误
 *
 * @className WebResponseState.java
 * @author pjf
 * @date 2019年10月11日 上午9:53:27
 */

public enum WebResponseState {
	/**
	 * 成功返回
	 */
	SUCCESS(200, "OK"),
	/**
	 * 错误返回
	 */
	BAD_REQUEST(400, "Bad Request"),
	/**
	 * 地址未找到
	 */
	NOT_FOUND(404, "page not found"),
	/**
	 * 服务错误及未定义的错误
	 */
	INTERNAL_SERVER_ERROR(500, "server internal error"),
	/**
	 * 其他已捕获的错误
	 */
	OTHER_ERROR(40001, "other error"),
	/**
	 * 未登录
	 */
	NOT_LOGIN(40002, "not login"),
	/**
	 * 用户名不能为空
	 */
	ACCOUNT_ERROR(40003, "user name does not exist"),
	/**
	 * 密码错误
	 */
	PASSWORD_ERROR(40004, "user password error"),
	/**
	 * 用户被锁定
	 */
	ACCOUNT_LOCKER(40005, "user locking"),
	/**
	 * zuul降级
	 */
	ZUUL_DOWN(40006, "zuul is downgrade"),
	/**
	 * 文件保存异常
	 */
	FILE_SAVE_ERR(40007, "file save err"),
	/**
	 * 判断为攻击
	 */
	ATTACK_VERIFICATION(40008, "attack verification limit"),
	/**
	 * 手机号码不存在
	 */
	MOBILE_PHONE_CODE_EXIST(40009, "mobilePhone code is exist"),
	/**
	 * 签名失败
	 */
	SIGNATURE_ERROR(40010, "signature error"),
	/**
	 * 网关错误
	 */
	GATEWAY_ERROR(40011, "gateway error"),
	/**
	 * 手机号已存在
	 */
	PHONE_NUMBER_EXIST(40012, "phone number exist"),
	/**
	 * 组织存在子集
	 */
	ORGANZIE_HAS_CHILD(40013, "organize has child"),

	LOGINTYPE_ERROR(40014, "must choose loginType"),
	/**
	 * 未找到匹配记录
	 */
	NOT_MATCHING_RECORD(40015, "no matching records found"),
	/**
	 * 运行或停止JOB失败
	 */
	RUN_JOB_ERR(40016, "run or stop job error"),
	/**
	 * JOBGROUP存在子集
	 */
	JOBGROUP_HAS_CHILD(40017, "jobGroup has child"),
	/*
	 * 存在未停止任务不可结束
	 */
	TIMING_TASKS(40018, "existing Timing Tasks"),
	/*
	 * 账户不存在
	 */
	ACCOUNT_NO_EXIST(40019, "account no exist"),

	ACCOUNT_NO_REAL(40020, "account no real name"),

	/**
	 * 文件保存异常
	 */
	NO_FILE(40021, "no file"),
	/*
	 * 交易数据重复
	 */
	DATA_REPEAT(40022, "data repeat"),
	/*
	 * 数据过期
	 */
	DATA_EXPIRE(40023, "data expire"),

	FILE_EMPTY(40024, "file cannot be empty"),
	// 请求上限
	RATE_LIMITER(40025, "rate limiter"),
	// 熔断
	CIRCUIT_BREAKER(40026, "circuit break"),
	// 数据重复
	DATE_REPEAT(40027, "data repeat"),
	// 数据存在
	DATA_EXIST(40028, "data exist"),
	/**
	 * 没有记录
	 */
	NO_DATA(40029, "no data exist"),
	// 队列异常
	MQ_ERROR(40030, "mq error"),
	// 手机验证码错误
	PHONE_CODE_ERROR(40031, "phone code error"),
	// 人脸
	FACE_ERROR(40032, "face error"),
	//人员未冻结
	FACE_NOT_FROZEN(40033, "face not frozen"),
	//验证码错误
	VERIFY_ERROR(40033,"verify error"),

	FACE_CANT_CHANGE(40034,"face can't change"),
    // 用户没有对应的考勤组
    ACCOUNT_NO_ATTENDANCE_GROUP(40301,"用户没有对应的考勤组"),
    // 选择天数限制在60天内
    TOO_MANY_SELECT_DAYS(40302,"选择天数限制在60天内"),



	;


    private int code;
	private String standardMessage;

	WebResponseState(int code, String standardMessage) {
		this.code = code;
		this.standardMessage = standardMessage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStandardMessage() {
		return standardMessage;
	}

	public void setStandardMessage(String standardMessage) {
		this.standardMessage = standardMessage;
	}
}
