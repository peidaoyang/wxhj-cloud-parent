/** 
 * @fileName: JobLogAlarmStatue.java  
 * @author: pjf
 * @date: 2019年11月21日 上午11:29:46 
 */

package com.wxhj.cloud.component.job;

/**
 * @className JobLogAlarmStatue.java
 * @author pjf
 * @date 2019年11月21日 上午11:29:46
 */

public enum JobLogAlarmStatue {
	SUCCESS(0), FAIL(1), FAIL_TIMEOUT(2), SUCCESS_ASYNC(3), INFO_LOG(4), ERR_LOG(5);

	private int statueCode;

	JobLogAlarmStatue(int statueCode) {
		this.statueCode = statueCode;
	}

	public int getStatueCode() {
		return statueCode;
	}

	public void setStatueCode(int statueCode) {
		this.statueCode = statueCode;
	}

}
