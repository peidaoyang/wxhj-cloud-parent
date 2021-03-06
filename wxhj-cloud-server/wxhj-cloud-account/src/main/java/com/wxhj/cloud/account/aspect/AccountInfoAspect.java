package com.wxhj.cloud.account.aspect;

import com.wxhj.cloud.account.domain.AccountInfoBakDO;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.runnable.FaceChangeSynchRunnable;
import com.wxhj.cloud.account.service.AccountInfoBakService;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.MapSceneAccountService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class AccountInfoAspect {
    @Resource
    AccountInfoBakService accountInfoBakService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    MapSceneAccountService mapSceneAccountService;
    @Resource
    FaceChangeRecService faceChangeRecService;


    @Pointcut("execution(public void com.wxhj.cloud.account.service.AccountInfoService.deleteCascade(..))")
    public void accountInfoDeleteCut() {
    }

    @Pointcut("execution(public void com.wxhj.cloud.account.service.AccountInfoService.updateCascade(..))")
    public void accountInfoUpdateInfoCut() {

    }

    @Before("accountInfoDeleteCut()")
    public void accountInfoDelete(JoinPoint joinPoint) {
        AccountInfoDO accountInfo = (AccountInfoDO) joinPoint.getArgs()[0];
        AccountInfoBakDO accountInfoBak = dozerBeanMapper.map(accountInfo, AccountInfoBakDO.class);
        accountInfoBakService.insert(accountInfoBak);
    }


    @After("accountInfoUpdateInfoCut()")
    public void accountInfoUpdateInfo(JoinPoint joinPoint) {
        AccountInfoDO accountInfo = (AccountInfoDO) joinPoint.getArgs()[0];
        List<String> sceneIdList = mapSceneAccountService.listSceneIdByAccountId(accountInfo.getAccountId());
        //.id(q)
        FaceChangeRecDO faceChangeRec = dozerBeanMapper.map(accountInfo, FaceChangeRecDO.class);
        if (!faceChangeRec.judgeLegally()) {
            return;
        }
        faceChangeRec.setMasterId(0L);

        List<FaceChangeRecDO> faceChangeRecList = sceneIdList.stream().map(q -> {
            FaceChangeRecDO faceChangeRecTemp = (FaceChangeRecDO) faceChangeRec.clone();
            faceChangeRecTemp.setId(q);
            faceChangeRecTemp.setOperateType(2);
            return faceChangeRecTemp;
        }).collect(Collectors.toList());
        //暂时注释
//        faceChangeRecService.deleteByAccountIdAndOperateType(accountInfo.getAccountId(),2);
        faceChangeRecService.insertListCascade(faceChangeRecList);

    }

}
