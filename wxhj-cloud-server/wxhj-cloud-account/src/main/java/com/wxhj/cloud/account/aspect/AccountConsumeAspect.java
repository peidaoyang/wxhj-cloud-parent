package com.wxhj.cloud.account.aspect;

import javax.annotation.Resource;

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

	@Pointcut("execution(public void com.wxhj.cloud.account.service.AccountConsumeService.insertCascade(..))")
	public void accountConsumeInsertCut() {
	}

	@After("accountConsumeInsertCut()")
	public void accountConsumeInsert(JoinPoint joinPoint) {
		AccountConsumeDO accountConsume = (AccountConsumeDO) joinPoint.getArgs()[0];
		accountInfoService.charging(accountConsume.getAccountId(), accountConsume.getConsumeMoney());
	}

}
