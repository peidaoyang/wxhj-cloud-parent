/** 
 * @fileName: RocketMQConfigConsumerListen.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:22:40 
 */

package com.wxhj.cloud.rocketmq.config;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.google.common.base.Strings;
import com.wxhj.cloud.rocketmq.RocketMqConsumer;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

import lombok.extern.slf4j.Slf4j;

/**
 * @className RocketMQConfigConsumerListen.java
 * @author pjf
 * @date 2019年10月17日 下午3:22:40   
*/
/**
 * @className RocketMQConfigConsumerListen.java
 * @author pjf
 * @date 2019年10月17日 下午3:22:40
 */
@Configuration
@Order
@Slf4j
public class RocketMqConfigConsumerListen implements ApplicationContextAware, InitializingBean {
	@Resource
	RocketMqConfig rocketMqConfig;
	private ConfigurableApplicationContext applicationContext;

	public RocketMqConfigConsumerListen() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Object> beans = this.applicationContext
				.getBeansWithAnnotation(RocketMqConsumerListenAnnotation.class);
		if (Objects.nonNull(beans)) {
			beans.forEach(this::registerContainer);
		}

	}

	private void registerContainer(String beanName, Object bean) {
		Class<?> clazz = AopUtils.getTargetClass(bean);
		//
		if (!RocketMqListenDoWorkHandle.class.isAssignableFrom(bean.getClass())) {
			throw new IllegalStateException(
					clazz + " is not instance of " + RocketMqListenDoWorkHandle.class.getName());
		}
		RocketMqListenDoWorkHandle rocketMqListenDoWorkHandle = (RocketMqListenDoWorkHandle) bean;

		RocketMqConsumerListenAnnotation rocketMqConsumerListenAnnotation = clazz
				.getAnnotation(RocketMqConsumerListenAnnotation.class);
		BeanDefinitionBuilder beanBuilder = BeanDefinitionBuilder
				.rootBeanDefinition(rocketMqConsumerListenAnnotation.rocketMQClass());
		if (!Strings.isNullOrEmpty(rocketMqConsumerListenAnnotation.rocketGroup())) {
			rocketMqConfig.setRocketGroup(rocketMqConsumerListenAnnotation.rocketGroup());
		}
		// beanBuilder.addConstructorArgValue(rocketMqConfig);

		//
		beanBuilder.addConstructorArgValue(rocketMqConfig);
		// .addConstructorArgValue(rocketMqConfig.getRocketGroup());

		String containerBeanName = String.format("%s_%s", RocketMqConsumer.class.getName(),
				rocketMqConfig.getRocketGroup());
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
		beanFactory.registerBeanDefinition(containerBeanName, beanBuilder.getBeanDefinition());

		RocketMqConsumer rocketMqConsume = beanFactory.getBean(containerBeanName,
				rocketMqConsumerListenAnnotation.rocketMQClass());

		if (!rocketMqConsume.isStarted()) {
			try {
				rocketMqConsume.setMessageModel(rocketMqConsumerListenAnnotation.messageModel());
				rocketMqConsume.setTopicAndSubExpression(rocketMqConsumerListenAnnotation.topic(),
						rocketMqConsumerListenAnnotation.subExpression());
				//
				rocketMqConsume.setSuccessTopic(rocketMqConsumerListenAnnotation.successTopic());
				rocketMqConsume.setErrorTopic(rocketMqConsumerListenAnnotation.errorTopic());
				//
				rocketMqConsume.setConsumerHandle(q -> {
					rocketMqListenDoWorkHandle.dataHandle(q);
				});

				rocketMqConsume.start();
			} catch (Exception e) {
				log.error("started rocketMqConsumer failed. {}", rocketMqConsume, e);
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}
