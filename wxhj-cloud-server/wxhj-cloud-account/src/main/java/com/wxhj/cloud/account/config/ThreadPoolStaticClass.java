package com.wxhj.cloud.account.config;

import java.util.concurrent.*;

/**
 * @author wxpjf
 * @date 2020/4/22 8:39
 */
public class ThreadPoolStaticClass {

    private static final FaceChangeExecutionHandler faceChangeExecutionHandler = new FaceChangeExecutionHandler();
    public static final ThreadPoolExecutor faceChangeThreadPool =
            new ThreadPoolExecutor(1,
                    1,
                    5,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue(1),
                    Executors.defaultThreadFactory(), faceChangeExecutionHandler);


    public static class FaceChangeExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

}
