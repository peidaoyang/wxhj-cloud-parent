/** 
 * @fileName: RocketMQConsumer.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:17:11 
 */

package com.wxhj.cloud.rocketmq;

import java.util.function.Consumer;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @className RocketMQConsumer.java
 * @author pjf
 * @date 2019年10月17日 下午3:17:11   
*/
/**
 * @className RocketMQConsumer.java
 * @author pjf
 * @date 2019年10月17日 下午3:17:11
 */

public interface RocketMqConsumer {
	void start();

	void disponse();

	void setConsumerHandle(Consumer<MessageExt> consumerHandle);

	void setTopicAndSubExpression(String topic, String subExpression);

	void setMessageModel(MessageModel messageModel);

	boolean isStarted();

	void setSuccessTopic(String successTopic);

	void setErrorTopic(String errorTopic);
}
