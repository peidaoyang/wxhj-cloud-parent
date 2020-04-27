package com.wxhj.cloud.redis.annotation;

import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.redis.annotation.entity.MethodInfo;
import com.wxhj.cloud.redis.annotation.util.UrlUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020/4/23 4:09 下午
 */
@Configuration
public class LogAnnotationListener implements ApplicationContextAware, InitializingBean {

    @Resource
    RedisTemplate redisTemplate;
    @Value("${spring.application.name}")
    String serverName;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LogAnnotationListener.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(LogAnnotationController.class);
        beans.forEach(this::getMethodInfo);
    }

    /**
     * 获取类中所有LogAnnotation注解的方法，并注册到redis
     *
     * @param beanName
     * @param bean
     * @return void
     * @author daxiong
     * @date 2020/4/24 10:36 上午
     */
    public void getMethodInfo(String beanName, Object bean) {
        Class<?> clazz = AopUtils.getTargetClass(bean);

        RequestMapping requestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
        if (requestMappingAnnotation == null) {
            return;
        }
        String[] controllerPaths = requestMappingAnnotation.value();

        Method[] methods = clazz.getDeclaredMethods();

        String redisKey = RedisKeyStaticClass.LOG_METHOD_INFO_KEY;
        // 先将redis中原来的key删掉
        if (redisTemplate.hasKey(redisKey)) {
            redisTemplate.delete(redisKey);
        }
        // 将映射信息存到redis
        for (Method method : methods) {
            registerMethod(controllerPaths, method, redisKey);
        }
    }

    /**
     * 将方法uri作为key，注册方法到redis中
     *
     * @param controllerPaths
     * @param method
     * @param redisKey
     * @return void
     * @author daxiong
     * @date 2020/4/24 10:37 上午
     */
    private void registerMethod(String[] controllerPaths, Method method, String redisKey) {
        if (controllerPaths.length == 0) {
            controllerPaths = new String[]{""};
        }
        LogAnnotationIgnore logAnnotationIgnore = method.getAnnotation(LogAnnotationIgnore.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        RequestMapping methodRequestMappingAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if (logAnnotationIgnore == null && apiOperation != null && methodRequestMappingAnnotation != null) {
            MethodInfo methodInfo = MethodInfo.builder().value(apiOperation.value()).name(method.getName()).build();
            String[] methodPaths = methodRequestMappingAnnotation.value();

            for (String controllerPath : controllerPaths) {
                for (String methodPath : methodPaths) {
                    String key = "/" + serverName + controllerPath + methodPath;
                    key = UrlUtil.urlFormat(key);
                    redisTemplate.opsForHash().put(redisKey, key, methodInfo);
                }
            }
        }
    }

}
