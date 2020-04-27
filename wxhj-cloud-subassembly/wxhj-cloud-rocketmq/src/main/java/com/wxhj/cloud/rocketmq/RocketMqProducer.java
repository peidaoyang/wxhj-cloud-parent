/** 
 * @fileName: RocketMQProducer.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:18:17 
 */

package com.wxhj.cloud.rocketmq;
/**
 * @className RocketMQProducer.java
 * @author pjf
 * @date 2019年10月17日 下午3:18:17   
*/
/** 
 * @className RocketMQProducer.java
 * @author pjf
 * @date 2019年10月17日 下午3:18:17 
*/

public interface RocketMqProducer {
		boolean sendMessage(String topic, String str);
		
		boolean sendMessage(String topic, byte[] strByte);

		/**
		 * 1s,5s,10s,30s,1m,2m,3m,4m,5m,6m,7m,8m,9m,10m,20m,30m,1h,2h level=0,表示不延时
		 * @author daxiong
		 * @date 2020/4/26 5:08 下午
		 * @param delayTimeLevel
		 * @param topic
		 * @param str
		 * @return boolean
		 */
		boolean sendDelayTimeMessage(int delayTimeLevel,String topic, String str);
		/**
		 * 
		 * @author pjf
		 * @date 2019年8月30日 下午1:24:33 
		 * @param topic	
		 * @param tags	用于消费筛选
		 * @param keys	用于指定队列
		 * @param str
		 * @return
		 */
		boolean sendMessage(String topic,String tags,String keys, String str);
		
		boolean sendMessage(String topic,String tags,String keys,byte[] strByte);
}
