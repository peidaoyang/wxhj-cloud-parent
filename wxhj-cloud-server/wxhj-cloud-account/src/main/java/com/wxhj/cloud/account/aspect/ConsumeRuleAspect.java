package com.wxhj.cloud.account.aspect;

import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.service.AccountCardInfoService;
import com.wxhj.cloud.account.service.AccountInfoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Aspect
@Component
public class ConsumeRuleAspect {

	@Resource
	AccountInfoService accountInfoService;

	@Pointcut("execution(* com.wxhj.cloud.account.service.ConsumeRuleService.insertCascade(..))")
	public void consumeRuleInsertCut() {
	}

	@AfterReturning("consumeRuleInsertCut()")
	public void accountConsumeInsert(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		List<String> sceneIdList = (List<String>) args[1];
	}

}
