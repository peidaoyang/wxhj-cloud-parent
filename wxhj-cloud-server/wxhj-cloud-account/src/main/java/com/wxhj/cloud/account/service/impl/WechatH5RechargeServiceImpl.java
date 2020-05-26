package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.WechatH5RechargeDO;
import com.wxhj.cloud.account.mapper.WechatH5RechargeMapper;
import com.wxhj.cloud.account.service.WechatH5RechargeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WechatH5RechargeServiceImpl implements WechatH5RechargeService {
    @Resource
    WechatH5RechargeMapper wechatH5RechargeMapper;

    @Override
    public WechatH5RechargeDO selectByOutTradeNo(String outTradeNo) {
        Example example = new Example(WechatH5RechargeDO.class);
        example.createCriteria().andEqualTo("outTradeNo", outTradeNo);
        List<WechatH5RechargeDO> wechatH5Recharges = wechatH5RechargeMapper.selectByExample(example);
        return wechatH5Recharges.size() == 0 ? null : wechatH5Recharges.get(0);

    }

    @Override
    public void insert(WechatH5RechargeDO wechatH5Recharge) {
        wechatH5RechargeMapper.insert(wechatH5Recharge);
    }

    @Override
    public void updateSuccessMark(String outTradeNo, Integer successMark, String message) {
        WechatH5RechargeDO wechatH5Recharge =
                new WechatH5RechargeDO();
        wechatH5Recharge.setOutTradeNo(outTradeNo);
        wechatH5Recharge.setIsSuccessMark(successMark);
        wechatH5Recharge.setErrMessage(message);
        wechatH5RechargeMapper.updateByPrimaryKeySelective(wechatH5Recharge);
    }
}
