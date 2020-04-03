package com.wxhj.cloud.account.listener;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;
import org.dozer.DozerBeanMapper;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.account.bo.AccountConsumeRocjetBO;
import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.service.AccountConsumeService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className AccountConsumeRecordListener.java
 * @author jwl
 * @date 2020年1月31日 下午5:16:25
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.ACCOUNT_CONSUME_TOPIC)
public class AccountConsumeRecordListener implements RocketMqListenDoWorkHandle {

	@Resource
	AccountConsumeService accountConsumeService;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		AccountConsumeRocjetBO accountConsumeRocjet = JSON.parseObject(bodyStr, AccountConsumeRocjetBO.class);
		AccountConsumeDO accountConsume = dozerBeanMapper.map(accountConsumeRocjet, AccountConsumeDO.class);
		accountConsumeService.insertCascade(accountConsume);
	}

}
