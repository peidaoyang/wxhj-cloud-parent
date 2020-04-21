/** 
 * @fileName: AsynJobPoolHelper.java  
 * @author: pjf
 * @date: 2019年11月21日 上午11:30:11 
 */

package com.wxhj.cloud.component.job;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * @className AsynJobPoolHelper.java
 * @author pjf
 * @date 2019年11月21日 上午11:30:11   
*/
/** 
 * @className AsynJobPoolHelper.java
 * @author pjf
 * @date 2019年11月21日 上午11:30:11 
*/

//@Component
//public class AsynJobPoolHelper {
//	private ThreadPoolExecutor slowTriggerPool = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS,
//			new LinkedBlockingQueue<Runnable>(2000), new ThreadFactory() {
//				@Override
//				public Thread newThread(Runnable r) {
//					return new Thread(r, "job, admin AsynJobPoolHelper-slowTriggerPool-" + r.hashCode());
//				}
//			});
//
//	public void addTrigger(AbstractAsynJobHandle asynJobHandle) {
//		slowTriggerPool.execute(asynJobHandle);
//	}
//}
