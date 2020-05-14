/**
 *
 */
package com.wxhj.cloud.account.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @ClassName: RechargeInfoDO.java
 * @author: cya
 * @Date: 2020年1月31日 下午3:13:53 
 */
@Table(name = "recharge_info")
@Data
public class RechargeInfoDO extends AbstractEntity<RechargeInfoDO>
        implements ICreationAudited, IModelInitialization {
    @Id
    //编号
    private String id;
    //用户id
    private String accountId;
    //充值金额
    private Integer amount;
    //手续费
    private Integer serviceAmount;
    //账户余额
    private Integer accountBalance;
    //支付来源（来自哪个公司）
    private Integer type;
    //支付类型（支付宝、微信）
    private Integer payType;
    //第三方支付流水号
    private String payNo;
    //预充值流水号
    private String prechargeNo;
    //组织id
    private String organizeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //创建时间
    private LocalDateTime creatorTime;
    //充值人员
    private String creatorUserId;

    private Integer isRevoke;

    private Integer rechargeMonth;

    @Override
    public void initialization() {
        type = 0;
        payType = 0;
        serviceAmount = 0;
        isRevoke = 0;
//		rechargeMonth = Integer.parseInt(DateUtil.getStringDate(this.creatorTime,"yyyyMM"));

        rechargeMonth = Integer.parseInt(DateFormat.getStringDate(this.creatorTime, "yyyyMM"));
    }
}
