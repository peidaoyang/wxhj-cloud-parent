package com.wxhj.cloud.account.listener;

import javax.annotation.Resource;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.domain.OrganizeCardPriorityDO;
import com.wxhj.cloud.account.service.AccountCardInfoService;
import com.wxhj.cloud.account.service.OrganizeCardPriorityService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieCommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import com.github.dozermapper.core.Mapper;

import com.wxhj.cloud.core.utils.JSON;
import com.wxhj.cloud.account.bo.AccountConsumeRocjetBO;
import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.service.AccountConsumeService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @className AccountConsumeRecordListener.java
 * @author jwl
 * @date 2020年1月31日 下午5:16:25
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.ACCOUNT_CONSUME_TOPIC)
@Slf4j
public class AccountConsumeRecordListener implements RocketMqListenDoWorkHandle {

	@Resource
	AccountConsumeService accountConsumeService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	OrganizeCardPriorityService organizeCardPriorityService;
	@Resource
	AccountCardInfoService accountCardInfoService;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		AccountConsumeRocjetBO accountConsumeRocket = JSON.parseObject(bodyStr, AccountConsumeRocjetBO.class);
		AccountConsumeDO accountConsume = dozerBeanMapper.map(accountConsumeRocket, AccountConsumeDO.class);

		// 按照卡交易顺序逐一判断，余额够就消费，不够就用最后一张卡消费，可以出现负值
		List<OrganizeCardPriorityDO> organizeCardPriorities = organizeCardPriorityService.listByOrganizeId(accountConsume.getOrganizeId());
		List<OrganizeCardPriorityDO> collect = organizeCardPriorities.stream().sorted(Comparator.comparingInt(OrganizeCardPriorityDO::getPriority)).collect(Collectors.toList());
		Integer consumeMoney = accountConsume.getConsumeMoney();
		List<AccountCardInfoDO> accountCardInfos = accountCardInfoService.selectByAccountId(accountConsume.getAccountId());
		Map<Integer, AccountCardInfoDO> map = accountCardInfos.stream().collect(Collectors.toMap(AccountCardInfoDO::getCardType, v -> v));
		for (int i = 0; i < collect.size(); i++) {
			OrganizeCardPriorityDO organizeCardPriority = collect.get(i);
			Integer cardType = organizeCardPriority.getCardType();
			AccountCardInfoDO accountCardInfo = map.get(cardType);
			if (accountCardInfo == null) {
				throw new WuXiHuaJieCommonException(WebResponseState.ACCOUNT_NO_CARD);
			}
			if (consumeMoney <= accountCardInfo.getBalance() || i == collect.size() - 1) {
				accountConsume.setCardType(cardType);
				accountConsumeService.insertCascade(accountConsume, accountCardInfo);
				return;
			}
		}
	}

}
