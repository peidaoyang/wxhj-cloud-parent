package com.wxhj.cloud.account.aspect;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.account.domain.AccountInfoBakDO;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.service.AccountInfoBakService;

@Aspect
@Component
public class AccountInfoAspect {
	@Resource
	AccountInfoBakService accountInfoBakService;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Pointcut("execution(public void com.wxhj.cloud.account.service.AccountInfoService.deleteCascade(..))")
	public void accountInfoDeleteCut() {
	}

	@Before("accountInfoDeleteCut()")
	public void accountInfoDelete(JoinPoint joinPoint) {
		AccountInfoDO accountInfo = (AccountInfoDO) joinPoint.getArgs()[0];
		AccountInfoBakDO accountInfoBak = dozerBeanMapper.map(accountInfo, AccountInfoBakDO.class);
		accountInfoBakService.insert(accountInfoBak);
	}
}
