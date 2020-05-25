package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;

import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/21 2:47 下午
 */
public interface AccountCardInfoService {

    /**
     * 根据账户id和卡类型获取数据
     *
     * @param accountId
     * @param cardType
     * @return com.wxhj.cloud.account.domain.AccountCardInfoDO
     * @author daxiong
     * @date 2020/5/21 2:58 下午
     */
    AccountCardInfoDO selectByAccountIdAndCardType(String accountId, Integer cardType);

    /**
     * 根据账户id获取数据
     *
     * @param accountId
     * @return com.wxhj.cloud.account.domain.AccountCardInfoDO
     * @author daxiong
     * @date 2020/5/21 2:58 下午
     */
    List<AccountCardInfoDO> selectByAccountId(String accountId);

    /**
     * 更新卡 充值信息
     *
     * @param accountCardInfo
     * @param amount
     * @return void
     * @author daxiong
     * @date 2020/5/21 3:15 下午
     */
    void recharge(AccountCardInfoDO accountCardInfo, Integer amount);

    /**
     * 更新
     *
     * @param accountCardInfo
     * @return void
     * @author daxiong
     * @date 2020/5/21 3:46 下午
     */
    void update(AccountCardInfoDO accountCardInfo);

    /**
     * 消费撤销
     *
     * @param accountCardInfo
     * @return void
     * @author daxiong
     * @date 2020/5/21 3:46 下午
     */
    void revoke(AccountCardInfoDO accountCardInfo, Integer amount);
}
