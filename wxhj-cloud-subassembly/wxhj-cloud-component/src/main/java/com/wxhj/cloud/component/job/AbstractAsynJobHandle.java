/** 
 * @fileName: AbstractAsynJobHandle.java  
 * @author: pjf
 * @date: 2019年11月21日 上午11:28:07 
 */

package com.wxhj.cloud.component.job;

import com.wxhj.cloud.feignClient.dto.CommonJobRequestDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @className AbstractAsynJobHandle.java
 * @author pjf
 * @date 2019年11月21日 上午11:28:07   
*/
/**
 * @className AbstractAsynJobHandle.java
 * @author pjf
 * @date 2019年11月21日 上午11:28:07
 */
@Slf4j
public abstract class AbstractAsynJobHandle implements Runnable {

	protected CommonJobRequestDTO commonJobRequest;

	// protected Integer execFailRetryCount = 0;

	@Override
	public void run() {
		Boolean isSuccess = false;

		try {
			System.out.println("job定时");
			writeLog("开始异步执行", JobLogAlarmStatue.INFO_LOG);
			isSuccess = execute();
		} catch (Exception ex) {
			isSuccess = false;
			writeLog(ex.getMessage(), JobLogAlarmStatue.ERR_LOG);
		} finally {
			destroy();
			if (isSuccess) {
				writeLog("执行完成", JobLogAlarmStatue.SUCCESS);
			} else {
				writeLog("执行失败", JobLogAlarmStatue.FAIL);
			}
		}
	}

	public abstract boolean execute();

	public abstract void destroy();

	public void writeLog(String message, JobLogAlarmStatue jobLogAlarmStatue) {
		if (jobLogAlarmStatue.equals(JobLogAlarmStatue.SUCCESS)) {
			log.info(message);
		} else {
			log.error(message);
		}
		// rocketMqProducer.sendMessage(RocketMqTopicStaticClass.JOB_LOG_TOPIC,
		// JSON.toJSONString(jobLogRequest));
	}

	public void setCommonJobRequest(CommonJobRequestDTO commonJobRequest) {
		this.commonJobRequest = commonJobRequest;
	}

}
