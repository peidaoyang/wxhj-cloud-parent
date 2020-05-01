package com.wxhj.cloud.gateway.thread;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.statics.BusinessStaticClass;
import com.wxhj.cloud.gateway.entity.AppLogAnnotationDO;
import com.wxhj.cloud.gateway.entity.DeviceLogAnnotationDO;
import com.wxhj.cloud.gateway.entity.WebLogAnnotationDO;
import com.wxhj.cloud.gateway.mapper.AppLogAnnotationEsMapper;
import com.wxhj.cloud.gateway.mapper.DeviceLogAnnotationEsMapper;
import com.wxhj.cloud.gateway.mapper.ApiLogAnnotationEsMapper;
import com.wxhj.cloud.redis.annotation.entity.MethodInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * @author daxiong
 * @date 2020/4/26 5:26 下午
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Slf4j
public class LogAnnotationThread implements Callable<String> {

    private MethodInfo methodInfo;

    @Resource
    ApiLogAnnotationEsMapper apiLogAnnotationEsMapper;
    @Resource
    DeviceLogAnnotationEsMapper deviceLogAnnotationEsMapper;
    @Resource
    AppLogAnnotationEsMapper appLogAnnotationEsMapper;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    @Override
    public String call() throws Exception {
        if (methodInfo == null) {
            return null;
        }
        try {
            insertByServer(methodInfo);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private void insertByServer(MethodInfo methodInfo) throws Exception {
        String serverName = methodInfo.getServerName();
        if (Strings.isNullOrEmpty(serverName)) {
            return;
        }
        if (BusinessStaticClass.PLATFORM_SERVER.equals(serverName)) {
            WebLogAnnotationDO logAnnotation = dozerBeanMapper.map(methodInfo, WebLogAnnotationDO.class);
            logAnnotation.putId(methodInfo.getId());
            apiLogAnnotationEsMapper.createIndex();
            apiLogAnnotationEsMapper.upsert(logAnnotation);
        }
        if (BusinessStaticClass.DEVICE_SERVER.equals(serverName)) {
            DeviceLogAnnotationDO deviceLogAnnotationDO = dozerBeanMapper.map(methodInfo, DeviceLogAnnotationDO.class);
            deviceLogAnnotationDO.putId(methodInfo.getId());
            deviceLogAnnotationEsMapper.createIndex();
            deviceLogAnnotationEsMapper.upsert(deviceLogAnnotationDO);
        }
        if (BusinessStaticClass.APP_SERVER.equals(serverName)) {
            AppLogAnnotationDO appLogAnnotationDO = dozerBeanMapper.map(methodInfo, AppLogAnnotationDO.class);
            appLogAnnotationDO.putId(methodInfo.getId());
            appLogAnnotationEsMapper.createIndex();
            appLogAnnotationEsMapper.upsert(appLogAnnotationDO);
        }
    }
}
