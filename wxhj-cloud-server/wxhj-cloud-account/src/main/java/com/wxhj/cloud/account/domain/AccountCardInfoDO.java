package com.wxhj.cloud.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户卡信息表(AccountCardInfo)实体类
 *
 * @author makejava
 * @since 2020-05-21 11:54:17
 */
@Data
@Builder
@Table(name = "account_card_info")
public class AccountCardInfoDO implements Serializable {
    private static final long serialVersionUID = -68445394942861004L;
    /**
    * 主键id
    */
    @Id
    private String id;
    /**
    * 关联account_id
    */
    private String accountId;
    /**
    * 根组织id
    */
    private String organizeId;
    /**
    * 卡号
    */
    private String cardNumber;
    /**
    * 卡类型
    */
    private Integer cardType;
    /**
    * 累计充值金额
    */
    private Integer rechargeTotalAmount;
    /**
    * 累计消费金额
    */
    private Integer consumeTotalAmount;
    /**
    * 累计消费次数
    */
    private Integer consumeTotalFrequency;
    /**
    * 账户余额
    */
    private Integer balance;
    /**
    * 卡有效期
    */
    private LocalDateTime validateTime;
    /**
    * 卡状态，0：正常、
    */
    private Integer status;


}