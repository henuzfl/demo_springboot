package com.zfl.demo.domain.processor;

import com.google.common.eventbus.Subscribe;
import com.zfl.demo.common.event.SampleEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SampleEventProcessor2 {

    @Subscribe
    public void dealWithEvent(SampleEvent event) {
        log.info("SampleEventProcessor2 received event: {}", event);
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            log.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }
        log.info("SampleEventProcessor2 dealWithEvent: {}", event);
    }
}
