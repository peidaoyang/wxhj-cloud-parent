package com.wxhj.cloud.account.listener;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.account.domain.WechatQrConsumeDO;
import com.wxhj.cloud.account.service.WechatQrConsumeService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;
import org.apache.rocketmq.common.message.MessageExt;
import org.dozer.DozerBeanMapper;

import javax.annotation.Resource;

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.ACCOUNT_CONSUME_TOPIC, rocketGroup = "accountServerWechatGroup")
public class WeChatQrTopicRecordListener implements RocketMqListenDoWorkHandle {
    //    @Resource
//    QrCodePaymentService qrCodePaymentService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    //    @Resource
//    OrganizePayInfoClient organizePayInfoClient;
    @Resource
    WechatQrConsumeService wechatQrConsumeService;

    @Override
    public void dataHandle(MessageExt messageExt) {
        String jsonStr = new String(messageExt.getBody());
        WechatQrConsumeDO wechatQrConsume = JSON.parseObject(jsonStr, WechatQrConsumeDO.class);
        wechatQrConsumeService.insert(wechatQrConsume);
    }
}
