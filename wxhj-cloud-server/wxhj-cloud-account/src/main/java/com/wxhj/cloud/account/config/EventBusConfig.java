package com.wxhj.cloud.account.config;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.wxhj.cloud.account.event.FaceChangeSynchEvent;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author wxpjf
 * @date 2020/4/21 13:33
 */
@SpringBootConfiguration

public class EventBusConfig {
    @Resource
    FaceChangeSynchEvent faceChangeSynchEvent;
    private static final RejectedExecutionHandler defaultHandler =
            new FaceChangeExecutionHandler();

    @Bean
    public EventBus faceChangeEventBus() {
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                5,
                TimeUnit.SECONDS,
                queue,
                Executors.defaultThreadFactory(), defaultHandler);
        EventBus eventBus = new AsyncEventBus(executor);
        eventBus.register(faceChangeSynchEvent);
        return eventBus;
    }

    public static class FaceChangeExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

}
