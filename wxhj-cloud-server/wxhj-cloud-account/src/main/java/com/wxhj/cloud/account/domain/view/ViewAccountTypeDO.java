package com.wxhj.cloud.account.domain.view;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Data
@Table(name = "view_account_type")
public class ViewAccountTypeDO {
    // 用户id
    @Id
    private String accountId;
    // 手机号
    private String phoneNumber;
    // 姓名
    private String name;
    // 身份证
    private String idNumber;
    // 性别
    private Integer sex;
    // 账户类型
    private Integer accountType;
    //账户类型名称
    private String accountTypeName;
    // 组织id
    private String organizeId;
    // 创建时间
    private Date createTime;
    // 累计充值金额
    private Integer rechargeTotalAmount;
    // 累计消费金额
    private Integer consumeTotalAmount;
    // 累计消费次数
    private Integer consumeTotalFrequency;
    // 账户余额
    private Integer accountBalance;
    // 账户有效期
    private Date accountValidity;
    // 备注
    private String memo;
    private String userPassword;
    private String userSecretKey;
    // 是否实名制
    private Integer isReal;
    // 是否人脸注册
    private Integer isFace;
    // 根组织
    private String childOrganizeId;

    private Integer isFrozen;

    private String otherCode;

    private String imageName;

    private String cardNumber;

}
