package com.wxhj.cloud.gateway.thread;

import com.wxhj.cloud.gateway.entity.LogAnnotationDO;
import com.wxhj.cloud.gateway.mapper.LogAnnotationEsMapper;
import com.wxhj.cloud.redis.annotation.entity.MethodInfo;
import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author daxiong
 * @date 2020/4/26 5:26 下午
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
public class LogAnnotationThread implements Callable<String> {

    private MethodInfo methodInfo;

    @Resource
    LogAnnotationEsMapper logAnnotationEsMapper;
    @Resource
    DozerBeanMapper dozerBeanMapper;

    @Override
    public String call() throws Exception {
        if (methodInfo == null) {
            return null;
        }
        try {
            LogAnnotationDO logAnnotation = dozerBeanMapper.map(methodInfo, LogAnnotationDO.class);
            logAnnotationEsMapper.createIndex();
            logAnnotationEsMapper.insertBatch(Arrays.asList(logAnnotation));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
