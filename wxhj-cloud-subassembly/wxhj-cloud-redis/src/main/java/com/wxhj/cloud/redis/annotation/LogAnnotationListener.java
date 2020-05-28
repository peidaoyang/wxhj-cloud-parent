package com.wxhj.cloud.redis.annotation;

import com.google.common.base.Charsets;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.redis.annotation.entity.MethodInfo;
import com.wxhj.cloud.redis.annotation.util.UrlUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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


    private List<ImmutablePair<String, MethodInfo>> pairList = new ArrayList<>(SystemStaticClass.INIT_CAPACITY);

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
        // 批量写入redis
        String redisKey = RedisKeyStaticClass.LOG_METHOD_INFO_KEY + RedisKeyStaticClass.REDIS_FOLDER_SYMBOL + serverName;
        // 先将redis中原来的key删掉
        if (redisTemplate.hasKey(redisKey)) {
            redisTemplate.delete(redisKey);
        }
        insertHashBatch(pairList, redisKey);
    }


    private void getSuperMethodAndRequestMapping(Class oriClass, MutablePair<Annotation, Method[]> pair) {
        Class superclass = oriClass.getSuperclass();
        if (superclass.equals(Object.class)) {
            return;
        }
        if (pair.getKey() == null) {
            pair.setLeft(superclass.getAnnotation(RequestMapping.class));
        }
        Method[] methods = ArrayUtils.addAll(superclass.getDeclaredMethods(), pair.getRight());
        pair.setRight(methods);
        getSuperMethodAndRequestMapping(superclass, pair);
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
        MutablePair<Annotation, Method[]> pair = new MutablePair<>(requestMappingAnnotation, clazz.getDeclaredMethods());
        getSuperMethodAndRequestMapping(clazz, pair);

        requestMappingAnnotation = (RequestMapping) pair.getLeft();
        String[] controllerPaths = pair.getLeft() == null ? new String[]{""} : requestMappingAnnotation.value();
        Method[] methods = pair.getRight();


        // 将映射信息存到内存
        for (Method method : methods) {
            registerMethod(controllerPaths, method);
        }

    }

    /**
     * 批量写入redis，类型为hash
     *
     * @param pairList
     * @param redisKey
     * @return void
     * @author daxiong
     * @date 2020/4/28 4:27 下午
     */
    private void insertHashBatch(List<ImmutablePair<String, MethodInfo>> pairList, String redisKey) {
        // pairList.forEach(item -> {
        redisTemplate.executePipelined(
                (RedisCallback<String>) connection -> {
                    pairList.forEach(item -> {
                        String key = item.getLeft();
                        MethodInfo method = item.getRight();
                        connection.hSet(redisKey.getBytes(Charsets.UTF_8), key.getBytes(Charsets.UTF_8),
                                redisTemplate.getValueSerializer().serialize(method));
                    });
                    return null;
                });
        //});
    }

    /**
     * 将方法uri作为key，注册方法到内存中
     *
     * @param controllerPaths
     * @param method
     * @return void
     * @author daxiong
     * @date 2020/4/24 10:37 上午
     */
    private void registerMethod(String[] controllerPaths, Method method) {
        LogAnnotationIgnore logAnnotationIgnore = method.getAnnotation(LogAnnotationIgnore.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        RequestMapping methodRequestMappingAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if (logAnnotationIgnore == null && apiOperation != null && methodRequestMappingAnnotation != null) {
            MethodInfo methodInfo = MethodInfo.builder().value(apiOperation.value()).name(method.getName()).serverName(serverName).build();
            String[] methodPaths = methodRequestMappingAnnotation.value();

            for (String controllerPath : controllerPaths) {
                for (String methodPath : methodPaths) {
                    String key = "/" + serverName + "/" + controllerPath + "/" + methodPath;
                    key = UrlUtil.urlFormat(key);
                    pairList.add(new ImmutablePair<>(key, methodInfo));
                }
            }
        }
    }

}
