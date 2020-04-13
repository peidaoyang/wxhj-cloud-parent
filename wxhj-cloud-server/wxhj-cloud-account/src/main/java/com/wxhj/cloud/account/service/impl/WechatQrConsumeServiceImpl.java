package com.wxhj.cloud.account.service.impl;

import com.wxhj.cloud.account.domain.WechatQrConsumeDO;
import com.wxhj.cloud.account.mapper.WechatQrConsumeMapper;
import com.wxhj.cloud.account.service.WechatQrConsumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WechatQrConsumeServiceImpl implements WechatQrConsumeService {
    @Resource
    WechatQrConsumeMapper wechatQrConsumeMapper;


    @Override
    public void insert(WechatQrConsumeDO wechatQrConsume) {
        wechatQrConsumeMapper.insert(wechatQrConsume);
    }
}
