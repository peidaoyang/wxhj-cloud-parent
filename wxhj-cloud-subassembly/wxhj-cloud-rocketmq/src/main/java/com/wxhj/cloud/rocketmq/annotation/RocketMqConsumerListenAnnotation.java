/** 
 * @fileName: RocketMQConsumerListenAnnotation.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:15:01 
 */

package com.wxhj.cloud.rocketmq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import com.wxhj.cloud.rocketmq.RocketMqConsumer;
import com.wxhj.cloud.rocketmq.def.DefRocketConsumerImpl;

/**
 * @className RocketMQConsumerListenAnnotation.java
 * @author pjf
 * @date 2019年10月17日 下午3:15:01
 */

@Target({ ElementType.TYPE })
@Inherited
@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface RocketMqConsumerListenAnnotation {
	String topic();

	String subExpression() default "*";

	MessageModel messageModel() default MessageModel.CLUSTERING;

	Class<? extends RocketMqConsumer> rocketMQClass() default DefRocketConsumerImpl.class;

	String rocketGroup() default "";

	String successTopic() default "";

	String errorTopic() default "";
}
