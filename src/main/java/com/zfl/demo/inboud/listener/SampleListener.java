package com.zfl.demo.inboud.listener;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.zfl.demo.common.event.SampleEvent;
import com.zfl.demo.domain.processor.SampleEventProcessor1;
import com.zfl.demo.domain.processor.SampleEventProcessor2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;

/**
 * 接受消息队列的事件，并使用EventBus进行异步处理
 *
 * @author 张飞龙
 */
@Service
public class SampleListener {

    EventBus eventBus;

    private final ExecutorService executorService;

    public SampleListener(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @PostConstruct
    public void initialize() {
        eventBus = new AsyncEventBus(this.executorService);
        eventBus.register(new SampleEventProcessor1());
        eventBus.register(new SampleEventProcessor2());
    }

    public void postSampleEvent(SampleEvent event) {
        eventBus.post(event);
    }
}
