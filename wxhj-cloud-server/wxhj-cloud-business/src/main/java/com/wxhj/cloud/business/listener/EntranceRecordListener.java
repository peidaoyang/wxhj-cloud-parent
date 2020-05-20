/**
 * @className EntranceRecordListener.java
 * @author jwl
 * @date 2020年1月16日 上午10:52:33
 */
package com.wxhj.cloud.business.listener;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;
import com.github.dozermapper.core.Mapper;

import com.wxhj.cloud.core.utils.JSON;
import com.wxhj.cloud.business.domain.EntranceDataDO;
import com.wxhj.cloud.business.service.EntranceDataService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className EntranceRecordListener.java
 * @author jwl
 * @date 2020年1月16日 上午10:52:33
 */
@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.ENTRANCE_TOPIC, rocketGroup = "businessServerEntranceGroup")
public class EntranceRecordListener implements RocketMqListenDoWorkHandle {
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	EntranceDataService entranceDataService;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		EntranceDataDO entranceDataDO = JSON.parseObject(bodyStr, EntranceDataDO.class);
		entranceDataService.insert(entranceDataDO);
	}
}
