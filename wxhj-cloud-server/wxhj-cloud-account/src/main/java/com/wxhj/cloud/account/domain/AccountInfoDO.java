/**
 * @fileName: AccountInfoDO.java
 * @author: pjf
 * @date: 2019年10月28日 下午2:59:44
 */

package com.wxhj.cloud.account.domain;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @className AccountInfoDO.java
 * @author pjf
 * @date 2019年10月28日 下午2:59:44
 */
@Table(name = "account_info")
@NoArgsConstructor
@Data
@ToString
@Slf4j
public class AccountInfoDO implements IModelInitialization {
    @Id
    // 用户id
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

    public void setIsReal(Integer real) {
        if (real == 1) {// 判断认证是否通过
            this.isReal = 1;
        } else {
            this.isReal = 0;
        }
    }

//    public void setOtherCode(String otherCode) {
//        if (Strings.isNullOrEmpty(otherCode)) {
//            this.otherCode = null;
//        } else {
//            this.otherCode = otherCode;
//        }
//    }

    @Override
    public void initialization() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        createTime = new Date();
        rechargeTotalAmount = 0;
        consumeTotalAmount = 0;
        consumeTotalFrequency = 0;
        accountBalance = 0;
        isReal = 0;
        isFace = 0;
        isFrozen = 0;
        try {
            accountValidity = sdf.parse("2200-12-31");
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
    }

//    public AccountInfoDO(String accountId, Integer isFace, String imageName) {
//        this.accountId = accountId;
//        this.isFace = isFace;
//        this.imageName = imageName;
//    }
}
