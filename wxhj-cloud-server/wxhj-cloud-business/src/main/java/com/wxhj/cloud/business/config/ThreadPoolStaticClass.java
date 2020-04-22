package com.wxhj.cloud.business.config;

import java.util.concurrent.*;

/**
 * @author wxpjf
 * @date 2020/4/22 9:15
 */
public class ThreadPoolStaticClass {
    private static final SummaryAttendanceExecutionHandler summaryAttendanceExecutionHandler = new SummaryAttendanceExecutionHandler();

    public static final ThreadPoolExecutor summaryAttendanceThreadPool =
            new ThreadPoolExecutor(1,
                    1,
                    5,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue(1),
                    Executors.defaultThreadFactory(), summaryAttendanceExecutionHandler);

    public static class SummaryAttendanceExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }
}
