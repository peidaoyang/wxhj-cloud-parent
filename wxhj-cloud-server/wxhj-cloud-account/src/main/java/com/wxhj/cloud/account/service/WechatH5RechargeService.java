package com.wxhj.cloud.account.service;

import com.wxhj.cloud.account.domain.WechatH5RechargeDO;

public interface WechatH5RechargeService {
    WechatH5RechargeDO selectByOutTradeNo(String outTradeNo);

    void insert(WechatH5RechargeDO wechatH5Recharge);

    void updateSuccessMark(String outTradeNo,Integer successMark,String message);
}
