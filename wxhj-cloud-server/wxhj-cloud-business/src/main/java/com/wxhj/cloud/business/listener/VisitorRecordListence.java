/** 
 * @fileName: VisitorRecordListence.java  
 * @author: pjf
 * @date: 2020年2月16日 下午2:26:03 
 */

package com.wxhj.cloud.business.listener;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.business.domain.VisitInfoDO;
import com.wxhj.cloud.business.service.visitor.VisitInfoService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className VisitorRecordListence.java
 * @author pjf
 * @date 2020年2月16日 下午2:26:03   
*/
/**
 * @className VisitorRecordListence.java
 * @author pjf
 * @date 2020年2月16日 下午2:26:03
 */
@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.VISITOR_TOPIC, rocketGroup = "businessServerVisitorGroup")
public class VisitorRecordListence implements RocketMqListenDoWorkHandle {
	@Resource
	VisitInfoService visitInfoService;

	@Override
	public void dataHandle(MessageExt messageExt) {
		String bodyStr = new String(messageExt.getBody());
		VisitInfoDO visitInfo = JSON.parseObject(bodyStr, VisitInfoDO.class);
		visitInfo.setCreatorTime(new Date());
		visitInfoService.insert(visitInfo);
	}
}
