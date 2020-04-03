/** 
 * @fileName: RocketFileUploadListener.java  
 * @author: pjf
 * @date: 2019年10月22日 下午4:07:54 
 */

package com.wxhj.cloud.file.listener;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;

import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqListenDoWorkHandle;
import com.wxhj.cloud.rocketmq.annotation.RocketMqConsumerListenAnnotation;

/**
 * @className RocketFileUploadListener.java
 * @author pjf
 * @date 2019年10月22日 下午4:07:54
 */

@RocketMqConsumerListenAnnotation(topic = RocketMqTopicStaticClass.FILEUPLOAD_TOPIC)
public class RocketFileUploadListener implements RocketMqListenDoWorkHandle {

	@Resource
	private FileStorageService fileStorageService;

	@Override
	public void dataHandle(MessageExt messageExt) {

		String uuid = new String(messageExt.getBody());

		fileStorageService.timedDeleteFile(uuid);

	}
}
