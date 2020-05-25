package com.wxhj.cloud.account.aspect;

import javax.annotation.Resource;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.service.AccountCardInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.service.AccountInfoService;

@Aspect
@Component
public class AccountConsumeAspect {

	@Resource
	AccountInfoService accountInfoService;
	@Resource
	AccountCardInfoService accountCardInfoService;

	@Pointcut("execution(public void com.wxhj.cloud.account.service.AccountConsumeService.insertCascade(..))")
	public void accountConsumeInsertCut() {
	}

	@After("accountConsumeInsertCut()")
	public void accountConsumeInsert(JoinPoint joinPoint) {
		AccountConsumeDO accountConsume = (AccountConsumeDO) joinPoint.getArgs()[0];
		accountInfoService.charging(accountConsume.getAccountId(), accountConsume.getConsumeMoney());

		AccountCardInfoDO accountCardInfo = (AccountCardInfoDO) joinPoint.getArgs()[1];
		accountCardInfo.setBalance(accountCardInfo.getBalance() - accountConsume.getConsumeMoney());
		accountCardInfo.setConsumeTotalFrequency(accountCardInfo.getConsumeTotalFrequency() + 1);
		accountCardInfo.setConsumeTotalAmount(accountCardInfo.getConsumeTotalAmount() + accountConsume.getConsumeMoney());
		accountCardInfoService.update(accountCardInfo);
	}

}
