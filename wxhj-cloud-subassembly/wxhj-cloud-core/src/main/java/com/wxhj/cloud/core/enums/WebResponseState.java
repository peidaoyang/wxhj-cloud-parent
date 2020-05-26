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
 * 	    404xx - account业务错误
 * 	    405xx - school业务错误
 * 	    406xx - 文件错误
 * 	    407xx - 支付错误
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
     * ------------ 401xx -	系统级别错误 ----------------
     */
    // 接口请求超时
    REQUEST_TIMEOUT(40101, "请求超时"),
    // 请求第三方接口失败
    REQUEST_THIRD_FAIL(40102, "请求第三方接口失败"),
    // 其他已捕获的错误
    OTHER_ERROR(40103, "other error"),
    // 未登录
    NOT_LOGIN(40104, "not login"),
    // 用户名不能为空
    ACCOUNT_ERROR(40105, "user name does not exist"),
    // 密码错误
    PASSWORD_ERROR(40106, "user password error"),
    // 用户被锁定
    ACCOUNT_LOCKER(40107, "user locking"),
    // zuul降级
    ZUUL_DOWN(40108, "zuul is downgrade"),
    // 判断为攻击
    ATTACK_VERIFICATION(40109, "attack verification limit"),
    // 签名失败
    SIGNATURE_ERROR(40110, "signature error"),
    // 网关错误
    GATEWAY_ERROR(40111, "gateway error"),
    // 组织存在子集
    ORGANIZE_HAS_CHILD(40112, "organize has child"),
    // 登录类型错误
    LOGIN_TYPE_ERROR(40113, "must choose loginType"),
    // 未找到匹配记录
    NOT_MATCHING_RECORD(40114, "no matching records found"),
    // 运行或停止JOB失败
    RUN_JOB_ERR(40115, "run or stop job error"),
    // JOB_GROUP存在子集
    JOB_GROUP_HAS_CHILD(40116, "jobGroup has child"),
    // 存在未停止任务不可结束
    TIMING_TASKS(40117, "existing Timing Tasks"),
    // 交易数据重复
    DATA_REPEAT(40118, "data repeat"),
    // 数据过期
    DATA_EXPIRE(40119, "data expire"),
    // 请求上限
    RATE_LIMITER(40120, "rate limiter"),
    // 熔断
    CIRCUIT_BREAKER(40121, "circuit break"),
    // 数据存在
    DATA_EXIST(40122, "data exist"),
    // 没有记录
    NO_DATA(40123, "no data exist"),
    // 队列异常
    MQ_ERROR(40124, "mq error"),
    // 请求超出限制
    REQUEST_LIMIT(40125, "request limit"),

    /**
     * --------------- 402xx - 参数级别错误 ---------------------
     */
    // 参数不能为空
    PARAMETER_MUST_NOT_NULL(40201,"参数不能为空"),

    /**
     * --------------- 403xx - business业务错误 ---------------------
     */
    // 用户没有对应的考勤组
    ACCOUNT_NO_ATTENDANCE_GROUP(40301,"用户没有对应的考勤组"),
    // 选择天数限制在62天内
    TOO_MANY_SELECT_DAYS(40302,"选择天数限制在62天内"),
    // 请假时间冲突
    CONFLICT_ASK_FOR_LEAVE(40303,"选择的时间段内已有审核中/审核成功的请假，时间冲突"),
    // 出差时间冲突
    CONFLICT_ON_BUSINESS(40304,"选择的时间段内已有审核中/审核成功的出差，时间冲突"),
    // 通行时间组下有通行规则
    ENTRANCEDAY_HAS_CHILD(40305,"通行时间组下有通行规则"),
    // 同组织下人员只存在一个考勤规则
    ACCOUNT_ATTENDANCE_ERROR(40306,"同组织下人员只存在一个考勤规则"),
    // 同组织下只能有一个自动同步考勤规则
    ATTENDANCE_AUTO_ERROR(40307,"同组织下只能有一个自动同步考勤规则"),


    /**
     * ------------------------- 404xx - account业务错误 ---------------------
     */
    FACE_CANT_CHANGE(40401,"face can't change"),
    // 场景中没有人员信息，打包无意义
    SCENE_NO_ACCOUNT(40402,"场景中没有人员信息，打包无意义"),
    // 该账户没有该类型的卡
    ACCOUNT_NO_CARD(40403,"该账户没有该类型的卡"),
    // 商户支付信息不存在
    NO_ORGANIZE_PAY_INFO(40404,"商户支付信息不存在"),
    // 账户已经被冻结
    ACCOUNT_FROZEN(40405,"账户已经被冻结"),
    // 余额不足
    BALANCE_NOT_ENOUGH(40406,"余额不足"),
    // 运营商权限有限
    ADMIN_ERROR(40407,"运营商权限有限"),
    // 手机号码不存在
    MOBILE_PHONE_CODE_EXIST(40408, "mobilePhone code is exist"),
    // 手机号已存在
    PHONE_NUMBER_EXIST(40409, "phone number exist"),
    // 账户不存在
    ACCOUNT_NO_EXIST(40410, "account no exist"),
    // 账户不是真实姓名
    ACCOUNT_NO_REAL(40411, "account no real name"),
    // 手机验证码错误
    PHONE_CODE_ERROR(40412, "phone code error"),
    // 人脸错误
    FACE_ERROR(40413, "face error"),
    // 人员未冻结
    FACE_NOT_FROZEN(40414, "face not frozen"),
    // 验证码错误
    VERIFY_ERROR(40415, "VERIFY_ERROR"),


    /**
     * ----------------- 405xx - school业务错误 ---------------
     */
    // 床位数不能小于入住人数
    SCHOOL_ROOM_EXCEED_ERROR(40501,"床位数不能小于入住人数"),
    // 学生只能入住一个宿舍
    SCHOOL_ROOM_ACCOUNT_EXCEED(40502,"学生只能入住一个宿舍"),
    // 人员同步失败
    SCHOOL_ROOM_ACCOUNT_ERROR(40503,"人员同步失败"),


    /**
     * ----------------- 406xx - 文件错误 --------------------
     */
    // 没有此文件
    NO_FILE(40601, "no file"),
    // 文件保存异常
    FILE_SAVE_ERR(40602, "file save err"),
    // 文件不能为空
    FILE_EMPTY(40603, "file cannot be empty"),



    /**
     * -------------------- 407xx - 支付错误 -------------------
     */
    // 订单不存在
    ORDER_NOT_EXISTENT(40701, "order not existent"),
    // 微信返回异常
    WECHAT_ERROR(40702, "weChat error"),


    /**
     * 添加前请看：
     * 错误类型对应业务类型
     * 		401xx -	系统级别错误
     * 		402xx - 参数级别错误
     * 		403xx - business业务错误
     * 	    404xx - account业务错误
     * 	    405xx - school业务错误
     * 	    406xx - 文件错误
     * 	    407xx - 支付错误
     */




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
