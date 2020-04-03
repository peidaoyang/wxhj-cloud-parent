/** 
 * @fileName: DefRocketProducerImpl.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:20:04 
 */

package com.wxhj.cloud.rocketmq.def;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @className DefRocketProducerImpl.java
 * @author pjf
 * @date 2019年10月17日 下午3:20:04
 */
@Slf4j
public class DefRocketProducerImpl implements RocketMqProducer {
	private DefaultMQProducer producer = null;

	private int delayTimeLevel = 0;

	public DefRocketProducerImpl(RocketMqConfig rocketMqConfig) {
		producer = new DefaultMQProducer(rocketMqConfig.getRocketGroup());
		producer.setNamesrvAddr(rocketMqConfig.getRocketServers());

		try {
			producer.start();
		} catch (MQClientException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean sendMessage(String topic, String str) {
		return sendMessage(topic, "", "", str);
	}

	@Override
	public boolean sendMessage(String topic, byte[] strByte) {
		// TODO Auto-generated method stub
		return sendMessage(topic, "", "", strByte);
	}

	@Override
	public boolean sendMessage(String topic, String tags, String keys, byte[] strByte) {
		try {
			Message msg = new Message(topic, tags, keys, strByte);
			if (delayTimeLevel > 0) {
				msg.setDelayTimeLevel(delayTimeLevel);
			}
			producer.send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean sendMessage(String topic, String tags, String keys, String str) {
		try {
			sendMessage(topic, tags, keys, str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void setDelayTimeLevel(int delayTimeLevel) {
		this.delayTimeLevel = delayTimeLevel;

	}

	@Override
	public boolean sendDelayTimeMessage(int delayTimeLevel, String topic, String str) {
		try {
			Message msg = new Message(topic, "", "", str.getBytes("utf-8"));
			if (delayTimeLevel > 0) {
				msg.setDelayTimeLevel(delayTimeLevel);
			}
			producer.send(msg);
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException | UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

}
