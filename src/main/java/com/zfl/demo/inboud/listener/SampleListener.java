package com.zfl.demo.inboud.listener;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.zfl.demo.common.event.SampleEvent;
import com.zfl.demo.domain.processor.SampleEventProcessor1;
import com.zfl.demo.domain.processor.SampleEventProcessor2;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 接受消息队列的事件，并使用EventBus进行异步处理
 *
 * @author 张飞龙
 */
@Service
public class SampleListener {

    EventBus eventBus;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public SampleListener(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    @PostConstruct
    public void initialize() {
        eventBus = new AsyncEventBus(this.threadPoolTaskExecutor);
        eventBus.register(new SampleEventProcessor1());
        eventBus.register(new SampleEventProcessor2());
    }

    public void postSampleEvent(SampleEvent event) {
        eventBus.post(event);
    }
}
