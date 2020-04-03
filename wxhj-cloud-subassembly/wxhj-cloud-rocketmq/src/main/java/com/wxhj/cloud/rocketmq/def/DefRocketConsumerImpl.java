/** 
 * @fileName: DefRocketConsumerImpl.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:19:02 
 */

package com.wxhj.cloud.rocketmq.def;

import java.util.List;
import java.util.function.Consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import com.google.common.base.Strings;
import com.wxhj.cloud.rocketmq.RocketMqConsumer;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @className DefRocketConsumerImpl.java
 * @author pjf
 * @date 2019年10月17日 下午3:19:02
 */
@Slf4j
public class DefRocketConsumerImpl implements RocketMqConsumer {
	private DefaultMQPushConsumer consumer;

	private Consumer<MessageExt> consumerHandle;
	private String topic;
	private String subExpression;
	private MessageModel messageModel = MessageModel.CLUSTERING;
	private boolean start = false;

	private String successTopic;
	private String errorTopic;

	//private RocketMqProducer rocketMqProducer;

	public DefRocketConsumerImpl(RocketMqConfig rocketMqConfig) {
		// 生成Consumer
		consumer = new DefaultMQPushConsumer(rocketMqConfig.getRocketGroup());
		// 配置Consumer
		consumer.setMessageModel(messageModel);
		consumer.setConsumeMessageBatchMaxSize(32);
		consumer.setNamesrvAddr(rocketMqConfig.getRocketServers());
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		//
		//rocketMqProducer = new DefRocketProducerImpl(rocketMqConfig);

	}

	@Override
	public void start() {
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
					ConsumeConcurrentlyContext consumeConcurrentlyContext) {
				// 消费消息
				for (MessageExt me : list) {
					try {
						consumerHandle.accept(me);
//						if (!Strings.isNullOrEmpty(successTopic)) {
//							rocketMqProducer.sendMessage(successTopic, me.getBody());
//						}
					} catch (Exception ex) {
//						if (!Strings.isNullOrEmpty(errorTopic)) {
//							rocketMqProducer.sendMessage(errorTopic, me.getBody());
//						}
						log.error(ex.getMessage());
					}
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		// 启动Consumer
		try {
			consumer.subscribe(topic, subExpression);
			consumer.start();
			start = true;
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	@Override
	public void disponse() {
		consumer.shutdown();
		start = false;
	}

	@Override
	public void setConsumerHandle(Consumer<MessageExt> consumerHandle) {
		this.consumerHandle = consumerHandle;

	}

	@Override
	public void setTopicAndSubExpression(String topic, String subExpression) {
		this.topic = topic;
		this.subExpression = subExpression;
	}

	@Override
	public void setMessageModel(MessageModel messageModel) {
		this.messageModel = messageModel;
	}

	@Override
	public boolean isStarted() {
		return start;
	}

	@Override
	public void setSuccessTopic(String successTopic) {
		this.successTopic = successTopic;
	}

	@Override
	public void setErrorTopic(String errorTopic) {
		this.errorTopic = errorTopic;
	}
}
