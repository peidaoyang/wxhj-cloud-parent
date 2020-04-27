package com.wxhj.cloud.core.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author daxiong
 * @date 2020-04-01 15:04
 */
@Component
@Data
@Slf4j
public class ThreadPoolHelper {

    // 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 5;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;

    /**
     * 创建线程池
     */
    static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("hj-thread-pool-%d").build();
    static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue(WORK_QUEUE_SIZE),
            namedThreadFactory);


    /**
     * 将任务加入线程池
     */
    public void executeTask(String taskId, Callable<String> callable) {
        threadPool.submit(callable);
    }

    /**
     * 终止订单线程池
     */
    public void shutdown() {
        threadPool.shutdown();
    }

}
