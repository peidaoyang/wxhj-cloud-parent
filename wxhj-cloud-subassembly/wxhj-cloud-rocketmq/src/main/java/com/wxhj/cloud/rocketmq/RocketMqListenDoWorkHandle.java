/** 
 * @fileName: RocketMQListenDoWorkHandle.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:17:59 
 */

package com.wxhj.cloud.rocketmq;

import org.apache.rocketmq.common.message.MessageExt;

/**
 * @className RocketMQListenDoWorkHandle.java
 * @author pjf
 * @date 2019年10月17日 下午3:17:59   
*/
/**
 * @className RocketMQListenDoWorkHandle.java
 * @author pjf
 * @date 2019年10月17日 下午3:17:59
 */

public interface RocketMqListenDoWorkHandle {
	public void dataHandle(MessageExt messageExt);
}
